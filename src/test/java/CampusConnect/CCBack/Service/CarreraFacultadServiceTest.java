package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Facultad;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class CarreraFacultadServiceTest {

    @Autowired
    private CarreraService service;

    @Autowired
    private FacultadesService fService;

    @Test
    @DisplayName("Prueba crear asignatura no funciona")
    public void pruebaCrearBuscar() {

        System.out.println("Prueba crear");

        String nombre_carrera = "hola";

        String nombre_facultad = "sistemas";

        // FACULTAD --------------------------------------------------------
        Facultad facultad = new Facultad();
        facultad.setNombre(nombre_facultad);

        Facultad fCreado = this.fService.create(facultad);

        // se retorna correctamente el objeto
        assertNotNull(fCreado);

        // que la informacion sea la misma
        assertEquals(fCreado.getNombre(), facultad.getNombre());

        Facultad fConseguido = this.fService.findById(fCreado.getId());
        // se verifica que el objeto fue creado en la bd
        assertNotNull(fConseguido);
        assertEquals(fCreado, fConseguido);

        // CARRERA ---------------------------------------------------------

        Carrera a = new Carrera();
        a.setNombre(nombre_carrera);

        Carrera cCreada = this.service.create(a, fCreado.getId());
        assertNotNull(cCreada);

        // se busca el objeto
        Carrera aConseguida = this.service.findById(cCreada.getId());
        assertNotNull(aConseguida);

        assertEquals(cCreada, aConseguida);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

}
