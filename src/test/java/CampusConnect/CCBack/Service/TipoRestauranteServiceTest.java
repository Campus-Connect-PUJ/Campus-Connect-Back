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

import CampusConnect.CCBack.Model.TipoRestaurante;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class TipoRestauranteServiceTest {

    @Autowired
    private TipoRestauranteService service;

    @Test
    @DisplayName("Prueba crear tipo restaurante no funciona")
    public void pruebaTipoRestaurante() {
        String tipo = "hola";

        // se crea con el servicio
        TipoRestaurante tipoRestaurante = new TipoRestaurante();
        tipoRestaurante.setTipo(tipo);

        TipoRestaurante trCreado = this.service.create(tipoRestaurante);

        assertEquals(trCreado.getTipo(), tipo);

        // se busca el objeto
        TipoRestaurante aConseguida = service.findById(trCreado.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, trCreado);
    }
}
