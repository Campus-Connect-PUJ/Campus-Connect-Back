package CampusConnect.CCBack.Service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import CampusConnect.CCBack.CcBackApplication;
import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CcBackApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ActividadServiceTest {

    @Autowired
    private ActividadService repository;

    @Test
    @DisplayName("Prueba no funciona")
    public void prueba() {
        ActividadService aService = new ActividadService();
        // assertThat
        Assert.assertEquals("A", "A");
    }

}
