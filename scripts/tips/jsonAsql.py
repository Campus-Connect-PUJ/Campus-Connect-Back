import json
import sys

# convertir

# postgres=# select * from tip;
#  id | descripcion | id_usuario
# - ---+-------------+------------
#
# postgres=# select * from tipo_aprendizaje;
#  id | descripcion
# ----+-------------
#
# postgres=# select * from tipo_aprendizaje_tip;
#  id_tip | id_tipo_aprendizaje
# --------+---------------------

# por si cambian los nombres de las tablas
TABLA_TIP                   = "tip"
TABLA_TIPO_APRENDIZAJE      = "tipo_aprendizaje"
TABLA_TIPO_APRENDIZAJE_TIP  = "tipo_aprendizaje_tip"

ID_TIP = 1
ID_TIPO_APRENDIZAJE = 1

TIPOS_APRENDIZAJE = {}

def tip_a_sql(tip) :
    global ID_TIP
    global ID_TIPO_APRENDIZAJE
    sqltip = (
        "insert into {}"
        "(id, descripcion, id_usuario)"
        " values ({}, '{}', {});"
    ).format(
        TABLA_TIP,
        ID_TIP,
        tip['descripcion'],
        tip['idUsuario']
    )

    print(sqltip)

    for tipo in tip['tipoAprendizaje']:
        if tipo not in TIPOS_APRENDIZAJE:
            TIPOS_APRENDIZAJE[tipo] = ID_TIPO_APRENDIZAJE
            ID_TIPO_APRENDIZAJE += 1

            sqltipo = (
                "insert into {}"
                "(id, descripcion)"
                " values ({},'{}');"
            ).format(
                TABLA_TIPO_APRENDIZAJE,
                str(TIPOS_APRENDIZAJE[tipo]),
                tipo
            )
            print(sqltipo)
        sqlintermedio = (
            "insert into {}"
            "(id_tip, id_tipo_aprendizaje)"
            " values ({}, {});"
        ).format(
            TABLA_TIPO_APRENDIZAJE_TIP,
            str(ID_TIP),
            str(TIPOS_APRENDIZAJE[tipo])
        )
        print(sqlintermedio)
    ID_TIP += 1

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

    for tip in grupos:
        tip_a_sql(tip)
