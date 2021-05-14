package CampusConnect.CCBack.Service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CampusConnect.CCBack.CcBackApplication;
import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Repository.ActividadRepository;
import junit.framework.Assert;

// @Profile("test")
// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = { CcBackApplication.class })
// @RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration

@ExtendWith(MockitoExtension.class)
public class ActividadServiceTest {

    ActividadService service ;

    @BeforeEach
    void initUseCase() {
        this.service = new ActividadService(
            Mockito.mock(ActividadRepository.class)
        );
    }

    @Test
    @DisplayName("Prueba no funciona")
    public void pruebaCrear() {
        this.service = new ActividadService(
            Mockito.mock(ActividadRepository.class)
        );

        System.out.println("Prueba crear");

        String nombre = "hola";

        // se crea con el servicio
        this.service.create(nombre);

        // se crea un objeto igual
        Actividad a = new Actividad();
        a.setNombre(nombre);

        // se busca el objeto
        Assert.assertEquals(service.findByName(nombre), "A");
    }

}
