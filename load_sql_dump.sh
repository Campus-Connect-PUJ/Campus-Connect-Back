#!/usr/bin/env bash

DOCKERCONTAINER='ccb_postgres'
DOCKERUSER='postgres'
DOCKERDATABASE='postgres'

SQLDUMPNAME="init.sql"

# carga un sql dump en la base de datos en docker
# recibe 1 parametro
#   ubicacion de sql dump
load () {
	cat $1 | docker exec -i $DOCKERCONTAINER psql -U $DOCKERUSER -d $DOCKERDATABASE
}

# llama al programa en la carpeta, llamado jsonAsql, pasandole como
# parametro un archivo json en esa misma carpeta. Todo esto es
# redirigido al archivo de salida
# recibe 3 parametros
#   carpeta
#   nombre json
#   archivo de salida
generate() {
	python "$1/jsonAsql.py" "$1/$2.json" > $3
}

BASE='./scripts'

PATHS=(
    'gruposEstudiantiles'
    'restaurantes'
    'usuariosGenerales'
    'tips'
)

for path in "${PATHS[@]}"; do
    generate "$BASE/$path" $path "$BASE/$path/$SQLDUMPNAME"
    load "$BASE/$path/$SQLDUMPNAME"
done
