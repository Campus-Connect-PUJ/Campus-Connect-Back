package CampusConnect.CCBack.Service;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;
import junit.framework.TestCase;

@ActiveProfiles("tests")

// en esta clase se realizan todas las pruebas que requieran de un usuario
@DataJpaTest
@RunWith(SpringRunner.class)
public class UsuarioGeneralTest extends TestCase {

    @Autowired
    private ForoService fService;

    @Autowired
    private UsuarioGeneralService ugService;

    private String emailUsuario = "email";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    // se corre antes de las demas pruebas
    @Before
    public void pruebaCreacionUsuario() {

        // se crea el usuario que sera usado en las demas pruebas
        WrapperUsuarioGeneral data = new WrapperUsuarioGeneral();

        data.setEmail(this.emailUsuario);
        data.setPassword("password");
        data.setNombre("nombre");
        data.setApellido("apellido");
        data.setSemestre(10);


        UsuarioGeneral ugCreado = ugService.create(data);
        assertNotNull(ugCreado);

        assertThat(ugCreado.getPassword(), is(not(data.getPassword())));
        // assertEquals(ugCreado.getSemestre(), data.getSemestre());

        assertAll(
            () -> assertEquals(ugCreado.getEmail()   , data.getEmail()),
            () -> assertEquals(ugCreado.getNombre()  , data.getNombre()),
            () -> assertEquals(ugCreado.getApellido(), data.getApellido())
            );

        UsuarioGeneral ugConseguido = ugService.findByEmail(this.emailUsuario);

        assertEquals(ugConseguido, ugCreado);

    }

    @Test
    public void pruebaForos() {

        String titulo = "titulo";
        String descripcion = "descripcion";

        Foro foro = new Foro();
        foro.setTitulo(titulo);
        foro.setDescripcion(descripcion);

        // se crea con el servicio
        Foro fCreado = this.fService.create(foro, emailUsuario);
        assertAll(
            () -> assertEquals(fCreado.getTitulo()     , foro.getTitulo()),
            () -> assertEquals(fCreado.getDescripcion(), foro.getDescripcion())
            );

        // se busca el objeto
        Foro fConseguido = fService.findById(fCreado.getId());
        assertNotNull(fConseguido);
        assertEquals(fCreado, fConseguido);
    }

}
