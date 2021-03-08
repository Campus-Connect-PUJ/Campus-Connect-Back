import json
import sys

# convertir

# postgres=# select * from lugar;
#  id | nombre | ubicacion
# ----+--------+-----------
#
# postgres=# select * from restaurante ;
#  id | calificacion | descripcion | descripcion_lugar | franquicia | nombre | precio_max | precio_min | tiempo_entrega | id_lugar
# ----+--------------+-------------+-------------------+------------+--------+------------+------------+----------------+----------
#
# postgres=# select * from tipo_restaurante ;
#  id | tipo
# ----+------
#
# postgres=# select * from regimen_alimenticio;
#  id | tipo
# ----+------
#
# postgres=# select * from tipos_restaurante ;
#  id_restaurante | id_tipo_restaurante
# ----------------+---------------------
#
# postgres=# select * from regimenes_alimenticios_restaurante;
 # id_restaurante | id_regimen_alimenticio
# ----------------+------------------------

# por si cambian los nombres de las tablas
TABLA_LUGARES             = "lugar"
TABLA_RESTAURANTES        = "restaurante"
TABLA_TIPO_RESTAURANTE    = "tipo_restaurante"
TABLA_REGIMEN             = "regimen_alimenticio"
TABLA_REGIMEN_RESTAURANTE = "regimenes_alimenticios_restaurante"
TABLA_TIPOS_RESTAURANTE   = "tipos_restaurante"

def lugar_a_sql(lugar) :
    sqllugar = (
        "insert into {}"
        "(id, nombre, ubicacion)"
        " values ({}, '{}', '{}');"
    ).format(
        TABLA_LUGARES,
        lugar['id'],
        lugar['nombre'],
        lugar['descripcion']
    )

    print(sqllugar)



# en caso de que hayan repetidas
TIPO_RESTAURANTE = {}
REGIMEN_ALIMENTICIO = {}

ID_TIPO = 1
ID_RESTAURANTE = 1
ID_REGIMEN = 1

def restaurante_a_sql(restaurante):
    global ID_RESTAURANTE
    global ID_TIPO
    global ID_REGIMEN

    sqlrestaurante = (
        "insert into {}"
        "(id, calificacion, descripcion, descripcion_lugar, franquicia, nombre, precio_max, precio_min, tiempo_entrega, id_lugar)"
        " values ({}, {}, '{}', '{}', '{}', '{}', {}, {}, {}, {});"
    ).format(
        TABLA_RESTAURANTES,
        ID_RESTAURANTE,
        restaurante['calificacion'],
        restaurante['descripcion'],
        restaurante['localizacion']['descripcion'],
        restaurante['franquicia'],
        restaurante['nombre'],
        restaurante['precioMax'],
        restaurante['precioMin'],
        restaurante['tiempoEntrega'],
        restaurante['localizacion']['id']
    )
    print(sqlrestaurante)

    for tipo in restaurante['tipo']:
        if tipo not in TIPO_RESTAURANTE:
            TIPO_RESTAURANTE[tipo] = ID_TIPO
            ID_TIPO += 1

            sqltipo = (
                "insert into {}"
                "(id, tipo)"
                " values ({},'{}');"
            ).format(
                TABLA_TIPO_RESTAURANTE,
                str(TIPO_RESTAURANTE[tipo]),
                tipo
            )
            print(sqltipo)
        sqlintermedio = (
            "insert into {}"
            "(id_restaurante, id_tipo_restaurante)"
            " values ({}, {});"
        ).format(
            TABLA_TIPOS_RESTAURANTE,
            str(ID_RESTAURANTE),
            str(TIPO_RESTAURANTE[tipo])
        )
        print(sqlintermedio)

    for regimen in restaurante['regimenAlimenticio']:
        if regimen not in REGIMEN_ALIMENTICIO:
            REGIMEN_ALIMENTICIO[regimen] = ID_REGIMEN
            ID_REGIMEN += 1

            sqlregimen = (
                "insert into {}"
                "(id, tipo)"
                " values ({},'{}');"
            ).format(
                TABLA_REGIMEN,
                str(REGIMEN_ALIMENTICIO[regimen]),
                regimen
            )
            print(sqlregimen)

        sqlintermedio = (
            "insert into {}"
            "(id_restaurante, id_regimen_alimenticio)"
            " values ({}, {});"
        ).format(
            TABLA_REGIMEN_RESTAURANTE,
            str(ID_RESTAURANTE),
            str(REGIMEN_ALIMENTICIO[regimen])
        )
        print(sqlintermedio)
    ID_RESTAURANTE += 1

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

    print ( "-- ubicaciones" )
    for lugar in grupos['ubicaciones']:
        lugar_a_sql(lugar)

    print ( "-- restaurantes" )
    for restaurante in grupos['restaurantes']:
        restaurante_a_sql(restaurante)
