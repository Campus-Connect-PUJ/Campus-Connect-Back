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

import CampusConnect.CCBack.Model.Caracteristica;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class CaracteristicasServiceTest {

    @Autowired
    private CaracteristicasService service;

    @Test
    @DisplayName("Prueba crear asignatura no funciona")
    public void pruebaCrearBuscar() {

        System.out.println("Prueba crear");

        String nombre = "hola";

        // se crea con el servicio
        Caracteristica a = new Caracteristica();
        a.setNombre(nombre);

        Caracteristica creado = this.service.create(a);

        // se busca el objeto
        Caracteristica aConseguida = this.service.findById(creado.getId());

        assertNotNull(aConseguida);

        System.out.println("conseguida: " + aConseguida);
        GenericServiceTest.printAll(aConseguida);

        System.out.println("pre: " + aConseguida);
        GenericServiceTest.printAll(a);

        assertEquals(aConseguida.getNombre(), a.getNombre());
        assertEquals(aConseguida, creado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

}
