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
import CampusConnect.CCBack.Model.Caracteristica;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class CaracteristicasServiceTest {

    @Autowired
    private CaracteristicasService service;

    @Test
    @DisplayName("Prueba crear caracteristica no funciona")
    public void pruebaCaracteristica() {
        String nombre = "hola";

        // se crea con el servicio
        Caracteristica caract = new Caracteristica();
        caract.setNombre(nombre);

        Caracteristica aCreada = this.service.create(caract);

        assertEquals(aCreada.getNombre(), nombre);

        // se busca el objeto
        Caracteristica aConseguida = service.findById(aCreada.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, aCreada);
    }

}
