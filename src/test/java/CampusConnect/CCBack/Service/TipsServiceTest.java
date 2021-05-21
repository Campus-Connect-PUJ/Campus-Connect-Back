package CampusConnect.CCBack.Service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.UsuarioMonitor;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class TipsServiceTest {
    @Autowired
    private UsuarioMonitorService service;

    @Test
    @DisplayName("Prueba crear tips no funciona")
    public void pruebaTips() {

    }
}
