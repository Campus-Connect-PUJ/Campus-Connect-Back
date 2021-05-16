package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CampusConnect.CCBack.CcBackApplication;
import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Repository.ActividadRepository;

import org.springframework.test.context.ActiveProfiles;

// @Profile("test")
// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = { CcBackApplication.class })
// @RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration

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

    private void printAll(Object o) {
        for (var field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = null;
			try {
				value = field.get(o);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.printf("%s: %s%n", name, value);
        }
    }

    @Test
    @DisplayName("Prueba no funciona")
    public void pruebaCrear() {

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
        printAll(aConseguida);

        System.out.println("pre: " + aConseguida);
        printAll(a);

        assertEquals(aConseguida.getNombre(), a.getNombre());
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

}
