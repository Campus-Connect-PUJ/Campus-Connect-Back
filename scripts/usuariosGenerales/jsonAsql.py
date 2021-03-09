import json
import sys

# convertir

# postgres=# select * from usuario_general;
#  id | correo | nombre | semestre
# ----+--------+--------+----------
#
# postgres=# select * from informacion_usuario;
#  id | fecha_nacimiento | identidad_genero | lugar_origen | perfil_contexto | raza | id_usuario
# ----+------------------+------------------+--------------+-----------------+------+------------

# por si cambian los nombres de las tablas
TABLA_USUARIOS             = "usuario_general"
TABLA_INFORMACION_USUARIOS = "informacion_usuario"

ID_USUARIO = 0

def usuario_a_sql(usuario):
    global ID_USUARIO

    sqlusuario = (
        "insert into {}"
        "(id, correo, nombre, semestre)"
        "values ({}, '{}', '{}', {});"
    ).format(
        TABLA_USUARIOS,
        ID_USUARIO,
        usuario['correo'],
        usuario['nombre'],
        usuario['semestre']
    )
    print(sqlusuario)
    sqlcaracteristica = (
        "insert into {}"
        "(id, fecha_nacimiento, identidad_genero, lugar_origen, perfil_contexto, raza, id_usuario)"
        "values ({}, '{}', '{}', '{}', '{}', '{}', {});"
    ).format(
        TABLA_INFORMACION_USUARIOS,
        ID_USUARIO,
        usuario['caracteristicas']['fechaNacimiento'],
        usuario['caracteristicas']['identidadGenero'],
        usuario['caracteristicas']['lugarOrigen'],
        usuario['caracteristicas']['perfilContexto'],
        usuario['caracteristicas']['raza'],
        ID_USUARIO
    )
    print(sqlcaracteristica)

    ID_USUARIO += 1

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

    for usuarios in grupos:
        usuario_a_sql(usuarios)
