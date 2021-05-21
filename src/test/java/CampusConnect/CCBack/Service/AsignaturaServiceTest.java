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

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class AsignaturaServiceTest {

    @Autowired
    private AsignaturaService service;

    @Test
    @DisplayName("Prueba crear asignatura no funciona")
    public void pruebaAsignatura() {
        String nombre = "hola";

        // se crea con el servicio
        Asignatura asig = new Asignatura();
        asig.setNombre(nombre);

        Asignatura aCreada = this.service.create(asig);

        assertEquals(aCreada.getNombre(), nombre);

        // se busca el objeto
        Asignatura aConseguida = service.findById(aCreada.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, aCreada);
    }

}
