import json
import sys

#     String nombre;
#     String descripcion;
#     String tematicas;
#     float calificacion;
#     float requisitos;
#     List<Caracteristica> caracteristicas;

# convertir

# postgres=# select * from grupo_estudiantil;
#  id | calificacion | descripcion | nombre | requisitos | tematicas
# ----+--------------+-------------+--------+------------+-----------

# postgres=# select * from caracteristicas_grupo_estudiantil;
#  id_grupo_estudiantil | id_caracteristica
# ----------------------+-------------------

# postgres=# select * from caracteristica;
#  id | nombre
# ----+--------

# por si cambian los nombres
GRUPO_ESTUDIANTIL = "grupo_estudiantil"
CARACTERISTICAS_GRUPO_ESTUDIANTIL = "caracteristicas_grupo_estudiantil"
CARACTERISTICA = "caracteristica"

# en caso de que hayan repetidas
CARACTERISTICAS = {}

ID_GRUPO = 1
ID_CARACTERISTICA = 1

def json_a_sql(json):
    global ID_GRUPO
    global ID_CARACTERISTICA
    global CARACTERISTICAS

    sqlgrupo = (
        "insert into {}"
        "(id, calificacion, descripcion, nombre, requisitos, tematicas)"
        " values ({}, {}, '{}', '{}', '{}', '{}');"
    ).format(
        GRUPO_ESTUDIANTIL,
        str(ID_GRUPO) ,
        json['calificacion'] ,
        json['descripcion'] ,
        json['nombre'] ,
        json['requisitos'] ,
        json['tematicas']
    )

    print(sqlgrupo)
    for caracteristica in json['caracteristicas']:

        # en caso de que la caracteristica aun no exista
        if caracteristica not in CARACTERISTICAS:
            CARACTERISTICAS[caracteristica] = ID_CARACTERISTICA
            ID_CARACTERISTICA += 1

            sqlcaracteristica = (
                "insert into {}"
                "(id, nombre)"
                " values ({},'{}');"
            ).format(
                CARACTERISTICA,
                str(CARACTERISTICAS[caracteristica]),
                caracteristica
            )
            print(sqlcaracteristica)

        sqlintermedio = (
            "insert into {}"
            "(id_grupo_estudiantil, id_caracteristica)"
            " values ({}, {});"
        ).format(
            CARACTERISTICAS_GRUPO_ESTUDIANTIL,
            str(ID_GRUPO),
            str(CARACTERISTICAS[caracteristica])
        )
        print(sqlintermedio)

    ID_GRUPO += 1
# fin --------------

if len(sys.argv) == 1:
    print("se debe especificar el archivo para leer")
    print("Ej:", sys.argv[0], "ejemplo.json")

else:
    archivo = sys.argv[1]

    # read file
    with open(archivo, 'r') as myfile:
        data=myfile.read()

    # parse file
    grupos = json.loads(data)

    for grupo in grupos:
        json_a_sql(grupo)
