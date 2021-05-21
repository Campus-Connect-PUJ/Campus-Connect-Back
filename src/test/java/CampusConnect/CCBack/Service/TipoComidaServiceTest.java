package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.TipoComida;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class TipoComidaServiceTest {

    @Autowired
    private TipoComidaService service;

    @Test
    @DisplayName("Prueba crear tipo comida no funciona")
    public void pruebaTipoRestaurante() {
        String tipo = "hola";

        // se crea con el servicio
        TipoComida tipoComida = new TipoComida();
        tipoComida.setTipo(tipo);

        TipoComida tcCreado = this.service.create(tipoComida);

        assertEquals(tcCreado.getTipo(), tipo);

        // se busca el objeto
        TipoComida aConseguida = service.findById(tcCreado.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, tcCreado);
    }
}
