package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Lugar;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class LugarServiceTest {

    @Autowired
    private LugarService service;

    @Test
    public void pruebaLugar() {

        String nombre = "hola";

        Lugar l = new Lugar();
        l.setNombre(nombre);

        // se crea con el servicio
        Lugar lCreado = this.service.create(l);

        assertEquals(lCreado.getNombre(), nombre);

        // se busca el objeto
        Lugar lConseguido = service.findById(lCreado.getId());
        assertNotNull(lConseguido);
        assertEquals(lConseguido, lCreado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }
}
