package CampusConnect.CCBack.Service;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Facultad;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class GruposEstudiantilesServiceTest {

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private CaracteristicasService cService;

    @Autowired
    private RequisitoService rService;

    @Autowired
    private TematicasService tService;

    @Autowired
    private FacultadesService fService;

    public void pruebaCaracteristicas() {

        String nombre = "hola";

        // se crea con el servicio
        Caracteristica a = new Caracteristica();
        a.setNombre(nombre);

        Caracteristica creado = this.cService.create(a);

        // se busca el objeto
        Caracteristica aConseguida = this.cService.findById(creado.getId());

        assertNotNull(aConseguida);
        assertEquals(aConseguida, creado);
    }

    public void pruebaTematicas() {

        String nombre = "hola";

        // se crea con el servicio
        Tematica a = new Tematica();
        a.setNombre(nombre);

        Tematica creado = this.tService.create(a);

        // se busca el objeto
        Tematica tConseguida = this.tService.findById(creado.getId());

        assertNotNull(tConseguida);
        assertEquals(tConseguida, creado);

    }

    public void pruebaFacultades() {

        String nombre = "hola";

        // se crea con el servicio
        Facultad a = new Facultad();
        a.setNombre(nombre);

        Facultad creado = this.fService.create(a);

        // se busca el objeto
        Facultad fConseguida = this.fService.findById(creado.getId());

        assertNotNull(fConseguida);
        assertEquals(fConseguida, creado);

    }

    public void pruebaRequisitos() {

        String nombre = "hola";

        // se crea con el servicio
        Requisito a = new Requisito();
        a.setNombre(nombre);

        Requisito creado = this.rService.create(a);

        assertEquals(creado.getNombre(), nombre);

        // se busca el objeto
        Requisito rConseguido = this.rService.findById(creado.getId());

        assertNotNull(rConseguido);
        assertEquals(rConseguido, creado);

    }

    public void pruebaGruposEstudiantiles() {
        String nombre = "grupo";
        String descripcion = "descripcion";

        GrupoEstudiantil ge = new GrupoEstudiantil();
        ge.setNombre(nombre);
        ge.setDescripcion(descripcion);

        // TODO: poner para que se cree el grupo con Caracteristicas,
        // Tematicas, Facultades y Requisitos

        WrapperGrupoEstudiantil dato = new WrapperGrupoEstudiantil();
        dato.setGrupoEstudiantil(ge);

        GrupoEstudiantil geCreado = this.geService.create(dato);

        // la informacion sea la misma
        assertAll(
            () -> assertEquals(geCreado.getNombre(), nombre),
            () -> assertEquals(geCreado.getDescripcion(), descripcion)
            );

        // se retorna correctamente el objeto
        assertNotNull(geCreado);
        GrupoEstudiantil geConseguido = this.geService.findById(geCreado.getId());
        // se verifica que el objeto fue creado en la bd
        assertNotNull(geConseguido);
        assertEquals(geCreado, geConseguido);

    }

    @Test
    public void pruebas() {
        pruebaCaracteristicas();
        pruebaTematicas();
        pruebaFacultades();
        pruebaRequisitos();
        pruebaGruposEstudiantiles();
    }

}
