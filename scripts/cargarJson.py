import json
import sys
import requests
from requests.auth import HTTPBasicAuth

BASEURL = 'http://localhost:8080/'

LOGINDATA = {}

def post(url, msg):
    # print(url)
    # print(json.dumps(msg, indent=4, sort_keys=True))
    #  ploads = {'username':'admin','password':'admin'}

    print(LOGINDATA)
    response = requests.post(
        url,
        headers={
            'Content-Type': 'application/json'
        },
        data=json.dumps(msg),
        cookies = LOGINDATA
    )

    data = json.loads(response.content)

    if 'error' in data:
        print("error:")
        print('\t', data['error'])
        quit()
    return data

def login ():
    global LOGINDATA
    url = BASEURL + 'login'
    print(url)
    ploads = {'username':'admin','password':'admin'}
    response = requests.post(
        url,
        params = ploads
    )

    print(response)
    print(response.headers)
    LOGINDATA = response.cookies
    # LOGINDATA = response.json()

    # print(json.loads(response.content))
    return response

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
    # login()
    with open(archivo, 'r') as myfile:
        data=myfile.read()

        # parse file
        datos = json.loads(data)

        id_admin = cargar_usuarios(datos['usuarios'])
        cargar_facultades_y_carreras(datos['facultades'], datos['carreras'])
        cargar_tips(datos['tips'], id_admin)
        cargar_grupos_estudiantiles(datos['grupos'])
        cargar_restaurantes(
            datos['restaurantes'],
            datos['ubicaciones'],
            datos['RegimenAlimenticio'],
            datos['TipoRestaurante'],
            datos['TipoCocina']
        )

def cargar_facultades_y_carreras(facultades, carreras):

    url = BASEURL + 'facultad'

    print(facultades)
    print("cargando facultades")
    facultades_json = {}
    for facultad in facultades:
        print (facultad)

        msggrp = {
            "nombre": facultad['nombre']
        }
        print(json.dumps(msggrp, indent=4, sort_keys=True))
        tmp = post(url, msggrp)
        print(tmp)
        facultades_json[facultad['id']] = tmp['id']
        print(facultades_json[facultad['id']])

    print("facultades", facultades_json)

    print("cargando carreras")
    url = BASEURL + 'carrera'
    for carrera in carreras:

        id_fac = facultades_json[carrera['idFac']]
        print(id_fac)
        msggrp = {
            "nombre": carrera['nombre']
        }
        print(json.dumps(msggrp, indent=4, sort_keys=True))
        print(post(url + '/{}'.format(id_fac) , msggrp))



def cargar_usuarios(usuarios):

    urlug = BASEURL + 'usuario'
    urliu = BASEURL + 'informacion_usuario/'

    print("cargando usuarios")
    id_admin = 1
    for usuario in usuarios:

        msg = {
            "nombre": usuario['nombre'],
            "password": usuario['password'],
            "apellido": usuario['apellido'],
            "correo": usuario['correo'],
            "semestre": usuario['semestre'],
            "password": usuario['password']
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
    """ya que todos los tips son con admin, se recibe el id del admin"""

    tipoAprendizaje = {}

    print("cargando tipos de aprendizaje")
    for tip in tips:
        tipoAprendizaje = agrupar(tipoAprendizaje, tip['tipoAprendizaje'], "descripcion", 'tipo_aprendizaje')

    url = BASEURL + 'tip'

    print("cargando tips")

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

    print("cargando caracteristicas, tematicas, facultades y requisitos")
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

    print("cargando grupos estudiantiles")
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

def cargar_restaurantes(
        restaurantes,
        ubicaciones_json,
        regimenes_alimenticios_json,
        tipos_restaurante_json,
        tipos_cocina_json
):
    # estos diccionarios son para traducir entre los valores que se
    # tienen localmente y los ids de los objetos que se tienen en la
    # bd

    ubicaciones = {}
    regimenes_alimenticios = {}
    tipos_restaurante = {}
    tipos_cocina = {}

    # se supone que no hay elementos repetidos en el json
    print("cargando lugares")
    url = BASEURL + 'lugar'
    for ubicacion in ubicaciones_json:
        msg = {
            "nombre": ubicacion['nombre'],
            "descripcion": ubicacion['descripcion']
        }
        ret = post(url, msg)
        print(ret)
        if 'error' not in ret:
            # guarda el id del objeto relacionado al id local
            ubicaciones[ubicacion['id']] = int(ret['id'])

    print("cargando regimenes alimenticios")
    url = BASEURL + 'regimen_alimenticio'
    for regimen_alimenticio in regimenes_alimenticios_json:
        msg = {
            "tipo": regimen_alimenticio['tipo'],
        }
        ret = post(url, msg)
        print(ret)
        if 'error' not in ret:
            # guarda el id del objeto relacionado al id local
            regimenes_alimenticios[regimen_alimenticio['tipo']] = int(ret['id'])

    print("cargando tipos de restaurante")
    url = BASEURL + 'tipo_restaurante'
    for tipo_cocina in tipos_restaurante_json:
        msg = {
            "tipo": tipo_cocina['tipo'],
        }
        ret = post(url, msg)
        print(ret)
        if 'error' not in ret:
            # guarda el id del objeto relacionado al id local
            tipos_restaurante[tipo_cocina['tipo']] = int(ret['id'])

    print("cargando tipos de comida")
    url = BASEURL + 'tipo_comida'
    for tipo_cocina in tipos_cocina_json:
        msg = {
            "tipo": tipo_cocina['tipo'],
        }
        ret = post(url, msg)
        print(ret)
        if 'error' not in ret:
            # guarda el id del objeto relacionado al id local
            tipos_cocina[tipo_cocina['tipo']] = int(ret['id'])

    print("cargando restaurantes")
    url = BASEURL + 'restaurante'
    for restaurante in restaurantes:

        # se convierte de los valores en el json a los ids

        loc = ubicaciones[restaurante['localizacion']['id']]

        regimenes_id = []
        for car in restaurante['regimenAlimenticio']:
            regimenes_id += [int(regimenes_alimenticios[car])]

        tipo_res_id = []
        for car in restaurante['tipoRestaurante']:
            tipo_res_id += [int(tipos_restaurante[car])]

        tipo_comida_id = []
        for car in restaurante['tipoComida']:
            tipo_comida_id += [int(tipos_cocina[car])]

        msg = {
            "restaurante": {
                "nombre": restaurante['nombre'],
                "descripcion" : restaurante['descripcion'],
                "ambientacion": 'no esta en el json',
                "descripcionlugar": restaurante['localizacion']['descripcion'],
                "franquicia": restaurante['franquicia'],
                "preciomax": restaurante['precioMax'],
                "preciomin": restaurante['precioMin'],
                "tiempoentrega": restaurante['tiempoEntrega']
            },
            "idLugar": loc,
            "tiposRestaurante": tipo_res_id,
            "regimenesAlimenticios": regimenes_id,
            "tiposComida": tipo_comida_id
        }

        # print(msg)
        print(post(url, msg))

if len(sys.argv) == 1:
    print("se debe especificar el archivo para leer")
    print("Ej:", sys.argv[0], "ejemplo.json")

else:
    main(sys.argv[1])
