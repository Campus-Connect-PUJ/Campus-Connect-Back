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

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class ActividadServiceTest {

    @Autowired
    private ActividadService service;

    // @BeforeEach
    // void initUseCase() {
    //     this.service = new ActividadService(
    //         Mockito.mock(ActividadRepository.class)
    //     );
    // }

    public ActividadServiceTest() {
        // this.service.create("a");
        // this.service.create("b");
        // this.service.create("c");
        // this.service.create("d");
        // this.service.create("e");
        // this.service.create("f");
    }

    @Test
    @DisplayName("Prueba crear actividad no funciona")
    public void pruebaCrearBuscar() {

        System.out.println("Prueba crear");

        String nombre = "hola";

        // se crea con el servicio
        this.service.create(nombre);

        // se crea un objeto igual
        Actividad a = new Actividad();
        a.setNombre(nombre);

        // se busca el objeto
        Actividad aConseguida = service.findByName(nombre);
        assertNotNull(aConseguida);

        System.out.println("conseguida: " + aConseguida);
        GenericServiceTest.printAll(aConseguida);

        System.out.println("pre: " + aConseguida);
        GenericServiceTest.printAll(a);

        assertEquals(aConseguida.getNombre(), a.getNombre());
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

}
