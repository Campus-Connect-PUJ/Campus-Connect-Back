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

def agrupar(dic, valores, tipo, locurl):
    """Guarda valores los valores despues de crearlos, evita que aparezcan repetidos"""
    url = BASEURL + locurl
    for valor in valores:
        if valor not in dic:
            # print(valor)
            msg = {
                tipo: valor
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
    cargar_grupos_estudiantiles(datos['grupos'])

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

def cargar_tips(tips, id_admin):
    """ya que todos los tips son con admin, se recibe un id"""

    tipoAprendizaje = {}

    for tip in tips:
        tipoAprendizaje = agrupar(tipoAprendizaje, tip['tipoAprendizaje'], "descripcion", 'tipo_aprendizaje')

    url = BASEURL + 'tip'

    for tip in tips:

        grcar = []
        for car in tip['tipoAprendizaje']:
            grcar += [int(tipoAprendizaje[car])]

        msggrp = {
            "idUsuario": id_admin,
            "tip": {
                "descripcion" : tip['descripcion']
            },
            "tiposAprendizaje": grcar
        }
        print(json.dumps(msggrp, indent=4, sort_keys=True))
        print(post(url, msggrp))

def cargar_grupos_estudiantiles(grupos):

    caracteristicas = {}
    tematicas = {}
    facultades = {}
    requisitos = {}

    for grupo in grupos:
        caracteristicas = agrupar(
            caracteristicas, grupo['caracteristicas'], "nombre", 'caracteristica')
        tematicas  = agrupar(tematicas,  grupo['tematicas'],  "nombre", 'tematica' )
        facultades = agrupar(facultades, grupo['facultades'], "nombre", 'facultad')
        requisitos = agrupar(requisitos, grupo['requisitos'], "nombre", 'requisito')

    # print("caracteristicas", json.dumps(caracteristicas, indent=4, sort_keys=True))
    # print("tematicas", json.dumps(tematicas, indent=4, sort_keys=True))
    # print("facultades", json.dumps(facultades, indent=4, sort_keys=True))
    # print("requisitos", json.dumps(requisitos, indent=4, sort_keys=True))

    url = BASEURL + 'grupo_estudiantil'

    for grupo in grupos:

        grcar = []
        for car in grupo['caracteristicas']:
            grcar += [int(caracteristicas[car])]
        grtem = []
        for car in grupo['tematicas']:
            grtem += [int(tematicas[car])]
        grfac = []
        for car in grupo['facultades']:
            grfac += [int(facultades[car])]
        grreq = []
        for car in grupo['requisitos']:
            grfac += [int(requisitos[car])]

        msggrp = {
            "grupoEstudiantil": {
                "nombre": grupo["nombre"],
                "descripcion" : grupo['descripcion']
            },
            "caracteristicas": grcar,
            "tematicas": grtem,
            "facultades": grfac,
	        "requisitos": grreq
        }
        print(post(url, msggrp))

def agrupar2(dic, valores, locurl):
    """Guarda valores los valores despues de crearlos, evita que aparezcan repetidos"""
    url = BASEURL + locurl
    for valor in valores:
        idv = valor['id']

        if idv not in dic:
            # print(valor)
            msg = {
                "nombre": valor['nombre'],
                "descripcion": valor['descripcion']
            }
            ret = post(url, msg)

            print(ret)

            if 'error' not in ret:
                dic[idv] = int(ret['id'])
    return dic

def cargar_restaurantes(restaurantes, ubicaciones_json):
    ubicaciones = {}
    for grupo in ubicaciones_json:
        ubicaciones = agrupar2(
            ubicaciones, ubicaciones_json, 'caracteristica')

if len(sys.argv) == 1:
    print("se debe especificar el archivo para leer")
    print("Ej:", sys.argv[0], "ejemplo.json")

else:
    main(sys.argv[1])
