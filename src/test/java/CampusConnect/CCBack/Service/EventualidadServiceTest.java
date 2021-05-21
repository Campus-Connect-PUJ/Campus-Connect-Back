
package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import CampusConnect.CCBack.Model.Eventualidad;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class EventualidadServiceTest {

    @Autowired
    private EventualidadService service;

    @Test
    @DisplayName("Prueba crear eventualidad no funciona")
    public void pruebaCrearBuscar() {

        String descripcion = "descripcion";

        Eventualidad eventualidad = new Eventualidad();
        eventualidad.setLongitud(5.0);
        eventualidad.setLatitud(5.0);
        eventualidad.setTipo(2);
        eventualidad.setDescripcion(descripcion);

        Eventualidad eCreado = this.service.create(eventualidad);

        // la informacion sea la misma
        assertAll(
            () -> assertEquals(eCreado.getLongitud(), eventualidad.getLongitud()),
            () -> assertEquals(eCreado.getLatitud(), eventualidad.getLatitud()),
            () -> assertEquals(eCreado.getTipo(), eventualidad.getTipo()),
            () -> assertEquals(eCreado.getDescripcion(), eventualidad.getDescripcion())
            );

        // se retorna correctamente el objeto
        assertNotNull(eCreado);
        Eventualidad eConseguido = this.service.findById(eCreado.getId());
        // se verifica que el objeto fue creado en la bd
        assertNotNull(eConseguido);
        assertEquals(eCreado, eConseguido);

        // borrar
        this.service.delete(eCreado);

        // ya no se encuentra el elemento
        Exception exception = assertThrows(
            ResponseStatusException.class,
            () -> {
                this.service.findById(eCreado.getId());
            }
        );
        String actualMessage = exception.getMessage();
        String mensajeEsperado = "404 NOT_FOUND \"Objeto con id " +
            eCreado.getId() +
            " no encontrado\"; nested exception is java.util.NoSuchElementException: No value present";
        // System.out.println("mensaje:::" + actualMessage);
        // System.out.println("finallll  mensaje");
        // System.out.println("mensaje:::" + mensajeEsperado);
        // System.out.println("finallll  mensaje");


        assertEquals(actualMessage, mensajeEsperado);


    }

}
