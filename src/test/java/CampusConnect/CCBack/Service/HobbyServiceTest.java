package CampusConnect.CCBack.Service;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import CampusConnect.CCBack.Model.Hobby;

class HobbyServiceTest {

    @Autowired
    private HobbyService hService;

    @Test
    public void pruebaHobbies() {

        String nombre = "hola";

        // se crea con el servicio
        Hobby creado = this.hService.create(nombre);

        assertEquals(creado.getNombre(), nombre);

        // se busca el objeto
        Hobby rConseguido = this.hService.findByName(nombre);

        assertNotNull(rConseguido);
        assertEquals(rConseguido, creado);

    }
}
