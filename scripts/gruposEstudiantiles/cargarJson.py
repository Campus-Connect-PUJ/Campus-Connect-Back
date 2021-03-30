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
                "nombre": valor
            }
            ret = post(url, msg)

            if 'error' not in ret:
                dic[valor] = int(ret['id'])
    return dic

def main(archivo):

    # read file
    with open(archivo, 'r') as myfile:
        data=myfile.read()

    # parse file
    grupos = json.loads(data)

    caracteristicas = {}
    tematicas = {}
    facultades = {}
    requisitos = {}

    for grupo in grupos:
        caracteristicas = agrupar(
            caracteristicas, grupo['caracteristicas'], 'caracteristica')
        tematicas  = agrupar(tematicas,  grupo['tematicas'], 'tematica' )
        facultades = agrupar(facultades, grupo['facultades'],'facultad')
        requisitos = agrupar(requisitos, grupo['requisitos'],'requisito')

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

if len(sys.argv) == 1:
    print("se debe especificar el archivo para leer")
    print("Ej:", sys.argv[0], "ejemplo.json")

else:
    main(sys.argv[1])
