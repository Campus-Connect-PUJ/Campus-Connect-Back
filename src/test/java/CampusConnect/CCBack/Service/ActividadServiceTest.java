package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Actividad;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class ActividadServiceTest {

    @Autowired
    private ActividadService service;

    @Test
    public void pruebaCrearBuscar() {

        String nombre = "hola";

        // se crea con el servicio
        Actividad aCreada = this.service.create(nombre);

        assertEquals(aCreada.getNombre(), nombre);

        // se busca el objeto
        Actividad aConseguida = service.findByName(nombre);
        assertNotNull(aConseguida);
        assertEquals(aConseguida, aCreada);
    }

}
