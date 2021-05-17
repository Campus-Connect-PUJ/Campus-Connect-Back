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
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;
import CampusConnect.CCBack.Model.Caracteristica;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class GruposEstudiantilesServiceTest {

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private CaracteristicasService cService;

    public void pruebaCaracteristicas() {
        System.out.println("Prueba crear");

        String nombre = "hola";

        // se crea con el servicio
        Caracteristica a = new Caracteristica();
        a.setNombre(nombre);

        Caracteristica creado = this.cService.create(a);

        // se busca el objeto
        Caracteristica aConseguida = this.cService.findById(creado.getId());

        assertNotNull(aConseguida);

        System.out.println("conseguida: " + aConseguida);
        GenericServiceTest.printAll(aConseguida);

        System.out.println("pre: " + aConseguida);
        GenericServiceTest.printAll(a);

        assertEquals(aConseguida.getNombre(), a.getNombre());
        assertEquals(aConseguida, creado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

    public void pruebaTematicas() {

    }

    public void pruebaFacultades() {

    }

    public void pruebaRequisitos() {

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
