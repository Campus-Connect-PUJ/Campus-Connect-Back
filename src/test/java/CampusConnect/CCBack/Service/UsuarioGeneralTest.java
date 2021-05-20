package CampusConnect.CCBack.Service;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.Horario;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Wrappers.WrapperHorario;
import CampusConnect.CCBack.Wrappers.WrapperInformacionUsuario;
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

    @Autowired
    private HorarioService hService;

    @Autowired
    private InformacionUsuarioService iuService;

    @Autowired
    private AsignaturaService aService;

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

    @Test
    public void pruebaInformacionUsuario() {

        // conseguir usuario
        WrapperInformacionUsuario wiu = new WrapperInformacionUsuario();

        LocalDate nacimiento = LocalDate.now();
        String religion = "religion";
        Boolean origen = true;
        String etnica = "etnica";
        String sexo = "sexo";
        String genero = "genero";

		wiu.setFechaNacimiento(nacimiento);
		wiu.setReligion(religion);
        wiu.setLocal(origen);
        wiu.setGrupoEtnico(etnica);
		wiu.setSexo(sexo);
		wiu.setGenero(genero);

        InformacionUsuario creado = this.iuService.cargarInformacionUsuario(
            wiu, this.emailUsuario);

        assertAll(
            () -> assertEquals(creado.getFechaNacimiento(), nacimiento),
            () -> assertEquals(creado.getIdentidadReligiosa(), religion),
            () -> assertEquals(creado.getLugarOrigen(), origen),
            () -> assertEquals(creado.getIdentidadEtnica(), etnica),
            () -> assertEquals(creado.getIdentidadSexo(), sexo),
            () -> assertEquals(creado.getIdentidadGenero(), genero)
        );

        // se busca el objeto
        InformacionUsuario iuConseguida = iuService.findById(creado.getId());
        assertNotNull(iuConseguida);
        assertEquals(iuConseguida, creado);

    }

    @Test
    public void pruebaAsignaturaHorario() {
        String lugar = "lugar";

        Asignatura a = pruebaAgregarAsignatura();

        WrapperHorario wph= new WrapperHorario();
        wph.setIdAsignatura(a.getId());
        wph.setLugar(lugar);
        wph.setFechaInicial(LocalDateTime.now());
        wph.setFechaFinal(LocalDateTime.now());

        // conseguir usuario
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);
        pruebaAgregarHorario(ug, wph);
    }

    public Asignatura pruebaAgregarAsignatura() {
        String nombre = "hola";
        String descripcion = "descripcion";

        // se crea con el servicio
        Asignatura a = new Asignatura();
        a.setNombre(nombre);
        a.setDescripcion(descripcion);

        Asignatura creado = this.aService.create(a);

        // se busca el objeto
        Asignatura aConseguida = aService.findById(creado.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, creado);

        return aConseguida;
    }

    public void pruebaAgregarHorario(UsuarioGeneral ug, WrapperHorario wph) {

        // se crea con el servicio
        Horario hCreado = this.hService.agregarHorariosMonitoria(ug, wph);

        UsuarioGeneral ugm = hCreado.getMonitor().getUsuario();
        assertAll(
            () -> assertEquals(hCreado.getFechaInicial() , wph.getFechaInicial()),
            () -> assertEquals(hCreado.getFechaFinal() , wph.getFechaFinal()),
            () -> assertEquals(hCreado.getLugar(), wph.getLugar()),
            () -> assertEquals(ugm, ug)
            );

        // se busca el objeto
        Horario hConseguido = hService.findById(hCreado.getId());
        assertNotNull(hConseguido);
        assertEquals(hConseguido, hCreado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

}
