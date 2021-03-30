import json
import sys
import requests

BASEURL = 'http://localhost:8080/'

def post(url, msg):
    # print(url)
    # print(json.dumps(msg, indent=4, sort_keys=True))
    response = requests.post(
        url,
        headers={
            'Content-Type': 'application/json'
        },
        data=json.dumps(msg)
    )
    return json.loads(response.content)

def agrupar(dic, valores, locurl):
    """Guarda valores los valores despues de crearlos, evita que aparezcan repetidos"""
    url = BASEURL + locurl
    for valor in valores:
        if valor not in dic:
            # print(valor)
            msg = {
                "descripcion": valor
            }
            ret = post(url, msg)

            print(ret)

            if 'error' not in ret:
                dic[valor] = int(ret['id'])
    return dic

def main(archivo):
    # read file
    with open(archivo, 'r') as myfile:
        data=myfile.read()

    # parse file
    datos = json.loads(data)

    id_admin = cargar_usuarios(datos['usuarios'])
    cargar_tips(datos['tips'], id_admin)

def cargar_usuarios(usuarios):

    urlug = BASEURL + 'usuario'
    urliu = BASEURL + 'informacion_usuario/'

    id_admin = 1
    for usuario in usuarios:

        msg = {
            "nombre": usuario['nombre'],
            "correo": usuario['correo'],
            "semestre": usuario['semestre']
        }

        ret = post(urlug, msg)

        print(ret)

        if 'error' not in ret:
            rr = post(
                urliu + "{}".format(ret['id']),
                usuario['caracteristicas']
            )
            id_admin = ret['id']
            print(rr)
    return(id_admin)

def cargar_tips(grupos, id_admin):
    """ya que todos los tips son con admin, se recibe un id"""

    tipoAprendizaje = {}

    for grupo in grupos:
        tipoAprendizaje = agrupar(tipoAprendizaje, grupo['tipoAprendizaje'], 'tipo_aprendizaje')

    url = BASEURL + 'tip'

    for grupo in grupos:

        grcar = []
        for car in grupo['tipoAprendizaje']:
            grcar += [int(tipoAprendizaje[car])]

        msggrp = {
            "idUsuario": id_admin,
            "tip": {
                "descripcion" : grupo['descripcion']
            },
            "tiposAprendizaje": grcar
        }
        print(json.dumps(msggrp, indent=4, sort_keys=True))
        print(post(url, msggrp))

if len(sys.argv) == 1:
    print("se debe especificar el archivo para leer")
    print("Ej:", sys.argv[0], "ejemplo.json")

else:
    main(sys.argv[1])
