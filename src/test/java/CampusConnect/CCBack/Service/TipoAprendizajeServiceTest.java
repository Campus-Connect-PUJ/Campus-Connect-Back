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

import CampusConnect.CCBack.Model.TipoAprendizaje;

@ActiveProfiles("tests")

@DataJpaTest
@RunWith(SpringRunner.class)
public class TipoAprendizajeServiceTest {
    @Autowired
    private TipoAprendizajeService service;

    @Test
    @DisplayName("Prueba crear tipo comida no funciona")
    public void pruebaTipoRestaurante() {
        String descripcion = "descripcion";
        TipoAprendizaje tipo = new TipoAprendizaje();
        tipo.setDescripcion(descripcion);
        
        TipoAprendizaje tipoCreado = this.service.create(tipo);


        assertEquals( tipoCreado.getDescripcion() , descripcion);
        // se retorna correctamente el objeto
        assertNotNull(tipoCreado);

        TipoAprendizaje tipoConseguido = this.service.findById(tipoCreado.getId());
        // se verifica que el objeto fue creado en la bd

        assertNotNull(tipoConseguido);
        assertEquals(tipoCreado, tipoConseguido);
    }
}
