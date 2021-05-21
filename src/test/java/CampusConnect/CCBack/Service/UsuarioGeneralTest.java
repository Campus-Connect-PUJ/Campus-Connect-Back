package CampusConnect.CCBack.Service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Facultad;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Horario;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.Lugar;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.Requisito;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.Tematica;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Wrappers.WrapperGrupoEstudiantil;
import CampusConnect.CCBack.Wrappers.WrapperHorario;
import CampusConnect.CCBack.Wrappers.WrapperInformacionUsuario;
import CampusConnect.CCBack.Wrappers.WrapperRespuestaForo;
import CampusConnect.CCBack.Wrappers.WrapperRestaurante;
import CampusConnect.CCBack.Wrappers.WrapperTip;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;
import junit.framework.TestCase;


// en esta clase se realizan todas las pruebas que requieran de un usuario
@ActiveProfiles("tests")
@DataJpaTest
@RunWith(SpringRunner.class)
public class UsuarioGeneralTest extends TestCase {

    @Autowired
    private ForoService fService;

    @Autowired
    private RespuestaForoService rfService;

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private UsuarioMonitorService umService;

    @Autowired
    private HorarioService hService;

    @Autowired
    private InformacionUsuarioService iuService;

    @Autowired
    private AsignaturaService aService;

    @Autowired
    private RegimenAlimenticioService raService;

    @Autowired
    private RegimenAlimenticioUsuarioService rauService;

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private CaracteristicasService cService;

    @Autowired
    private RequisitoService rService;

    @Autowired
    private TematicasService tService;

    @Autowired
    private FacultadesService faService;

    @Autowired
    private ResenhaGrupoEstudiantilService rgeService;

    @Autowired
    private ResenhaRestauranteService rrService;

    @Autowired
    private RestaurantesService resService;

    @Autowired
    private LugarService lService;

    @Autowired
    private TipsService temService;

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




    public long pruebaForos() {

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

        return fCreado.getId();
    }

    public void pruebaRespuestaForo(long idForo) {

        Foro foro = this.fService.findById(idForo);
        WrapperRespuestaForo wrf = new WrapperRespuestaForo();
        String texto = "texto";
        wrf.setTexto(texto);
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);

        // se crea con el servicio
        RespuestaForo rfCreado = this.rfService.create(wrf, foro, ug);
        assertAll(
            () -> assertEquals(rfCreado.getForo(), foro),
            () -> assertEquals(rfCreado.getUsuario(), ug),
            () -> assertEquals(rfCreado.getTexto(), texto)
            );

        // se busca el objeto
        RespuestaForo rfConseguido = rfService.findById(rfCreado.getId());
        assertNotNull(rfConseguido);
        assertEquals(rfCreado, rfConseguido);

    }

    @Test
    public void pruebasForos() {

        long idForo = pruebaForos();
        pruebaRespuestaForo(idForo);

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

    public long pruebaRegimenAlimenticio() {

        String tipo = "tipo";

        RegimenAlimenticio l = new RegimenAlimenticio();
		l.setTipo(tipo);

        // se crea con el servicio
        RegimenAlimenticio lCreado = this.raService.create(l);

        assertEquals(lCreado.getTipo(), tipo);

        // se busca el objeto
        RegimenAlimenticio lConseguido = raService.findById(lCreado.getId());
        assertNotNull(lConseguido);
        assertEquals(lConseguido, lCreado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);

        return lCreado.getId();
    }

    public void pruebaRegimenAlimenticioUsuario(long idregimen) {

        RegimenAlimenticio ra = this.raService.findById(idregimen);

        int exigencia = 1;
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);

        RegimenAlimenticioUsuario l = new RegimenAlimenticioUsuario();
		l.setRegimenAlimenticio(ra);
		l.setExigencia(exigencia);
        l.setUsuario(ug);

        // se crea con el servicio
        RegimenAlimenticioUsuario rauCreado = this.rauService.create(ra, exigencia, ug);

        assertEquals(rauCreado.getExigencia(), exigencia);
        assertEquals(rauCreado.getUsuario(), ug);
        assertEquals(rauCreado.getRegimenAlimenticio(), ra);

        // se busca el objeto
        RegimenAlimenticioUsuario rauConseguido = rauService.findById(rauCreado.getId());
        assertNotNull(rauConseguido);
        assertEquals(rauConseguido, rauCreado);
    }

    @Test
    public void pruebaRegimenes() {
        long idregimen = pruebaRegimenAlimenticio();
        pruebaRegimenAlimenticioUsuario(idregimen);
    }

    public void pruebaResenhaGrupoEstudiantil(UsuarioGeneral ug, long idGe) {

        ResenhaGrupoEstudiantil resenha = new ResenhaGrupoEstudiantil();
        float estrellas = 5;
		resenha.setEstrellas(estrellas);

        // se crea con el servicio
        ResenhaGrupoEstudiantil rgeCreada = this.rgeService.create(ug, resenha,idGe);

        assertEquals(rgeCreada.getEstrellas(), estrellas);
        assertEquals(rgeCreada.getUsuario(), ug);
        long idGeConseguido = rgeCreada.getGrupoEstudiantil().getId();
        assertEquals(idGeConseguido, idGe);

        // se busca el objeto
        ResenhaGrupoEstudiantil rgeConseguida = rgeService.findById(rgeCreada.getId());
        assertNotNull(rgeConseguida);
        assertEquals(rgeConseguida, rgeCreada);
    }

    public void pruebaCaracteristicas() {

        String nombre = "hola";

        // se crea con el servicio
        Caracteristica a = new Caracteristica();
        a.setNombre(nombre);

        Caracteristica creado = this.cService.create(a);

        // se busca el objeto
        Caracteristica aConseguida = this.cService.findById(creado.getId());

        assertNotNull(aConseguida);
        assertEquals(aConseguida, creado);
    }

    public void pruebaTematicas() {

        String nombre = "hola";

        // se crea con el servicio
        Tematica a = new Tematica();
        a.setNombre(nombre);

        Tematica creado = this.tService.create(a);

        // se busca el objeto
        Tematica tConseguida = this.tService.findById(creado.getId());

        assertNotNull(tConseguida);
        assertEquals(tConseguida, creado);

    }

    public void pruebaFacultades() {

        String nombre = "hola";

        // se crea con el servicio
        Facultad a = new Facultad();
        a.setNombre(nombre);

        Facultad creado = this.faService.create(a);

        // se busca el objeto
        Facultad fConseguida = this.faService.findById(creado.getId());

        assertNotNull(fConseguida);
        assertEquals(fConseguida, creado);

    }

    public void pruebaRequisitos() {

        String nombre = "hola";

        // se crea con el servicio
        Requisito a = new Requisito();
        a.setNombre(nombre);

        Requisito creado = this.rService.create(a);

        assertEquals(creado.getNombre(), nombre);

        // se busca el objeto
        Requisito rConseguido = this.rService.findById(creado.getId());

        assertNotNull(rConseguido);
        assertEquals(rConseguido, creado);

    }

    public long pruebaGruposEstudiantiles() {
        String nombre = "grupo";
        String descripcion = "descripcion";
        float calificacion = 5;
        String contacto = "contacto";

        GrupoEstudiantil ge = new GrupoEstudiantil();
		ge.setCalificacion(calificacion);
		ge.setContacto(contacto);
		ge.setDescripcion(descripcion);
        ge.setNombre(nombre);

        // TODO: poner para que se cree el grupo con Caracteristicas,
        // Tematicas, Facultades y Requisitos

        WrapperGrupoEstudiantil dato = new WrapperGrupoEstudiantil();
        dato.setGrupoEstudiantil(ge);

        GrupoEstudiantil geCreado = this.geService.create(dato);

        // la informacion sea la misma
        assertAll(
            () -> assertEquals(geCreado.getNombre(), nombre),
            () -> assertEquals(geCreado.getDescripcion(), descripcion),
            () -> assertEquals(geCreado.getContacto(), contacto),
            () -> assertEquals(geCreado.getCalificacion(), calificacion)
            );

        // se retorna correctamente el objeto
        assertNotNull(geCreado);
        GrupoEstudiantil geConseguido = this.geService.findById(geCreado.getId());
        // se verifica que el objeto fue creado en la bd
        assertNotNull(geConseguido);
        assertEquals(geCreado, geConseguido);

        return geCreado.getId();

    }

    @Test
    public void pruebasGruposEstudiantiles() {
        pruebaCaracteristicas();
        pruebaTematicas();
        pruebaFacultades();
        pruebaRequisitos();
        long idgrupoest = pruebaGruposEstudiantiles();
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);
        pruebaResenhaGrupoEstudiantil(ug, idgrupoest);
    }

    public void pruebaResenhaRestaurante(UsuarioGeneral ug, long idRestaurante) {

        ResenhaRestaurante resenha = new ResenhaRestaurante();
        float estrellas = 5;
		resenha.setEstrellas(estrellas);

        // se crea con el servicio
        ResenhaRestaurante rgeCreada = this.rrService.create(
            ug, resenha,idRestaurante);

        assertEquals(rgeCreada.getEstrellas(), estrellas);
        assertEquals(rgeCreada.getUsuario(), ug);
        long idGeConseguido = rgeCreada.getRestaurante().getId();
        assertEquals(idGeConseguido, idRestaurante);

        // se busca el objeto
        ResenhaRestaurante rgeConseguida = rrService.findById(rgeCreada.getId());
        assertNotNull(rgeConseguida);
        assertEquals(rgeConseguida, rgeCreada);
    }

    public long pruebaRestaurantes(long idLugar) {

        String ambientacion = "ambientacion";
        float calificacion = 5;
        String contacto = "contacto";
        String descripcion = "descripcion";
        String franquicia = "franquicia";
        String nombre = "nombre";
        float precioMax = 10;
        float precioMin = 5;
        float tiempoEntrega = 20;

        Restaurante restaurante = new Restaurante();
		restaurante.setAmbientacion(ambientacion);
		restaurante.setCalificacion(calificacion);
		restaurante.setContacto(contacto);
		restaurante.setDescripcion(descripcion);
		restaurante.setFranquicia(franquicia);
		restaurante.setNombre(nombre);
		restaurante.setPrecioMax(precioMax);
		restaurante.setPrecioMin(precioMin);
		restaurante.setTiempoEntrega(tiempoEntrega);

        WrapperRestaurante wr = new WrapperRestaurante();
        wr.setIdLugar(idLugar);
        wr.setRestaurante(restaurante);

        Restaurante resCreado = this.resService.create(wr);

        assertEquals(resCreado.getAmbientacion(), ambientacion);
        assertEquals(resCreado.getCalificacion(), calificacion);
        assertEquals(resCreado.getContacto(), contacto);
        assertEquals(resCreado.getDescripcion(), descripcion);
        assertEquals(resCreado.getFranquicia(), franquicia);
        assertEquals(resCreado.getNombre(), nombre);
        assertEquals(resCreado.getPrecioMax(), precioMax);
        assertEquals(resCreado.getPrecioMin(), precioMin);
        assertEquals(resCreado.getTiempoEntrega(), tiempoEntrega);

        Restaurante resConseguido = this.resService.findById(resCreado.getId());
        assertEquals(resConseguido, resCreado);

        return resConseguido.getId();

    }

    public long pruebaLugar() {
        String nombre = "hola";

        Lugar l = new Lugar();
        l.setNombre(nombre);

        // se crea con el servicio
        Lugar lCreado = this.lService.create(l);

        assertEquals(lCreado.getNombre(), nombre);

        // se busca el objeto
        Lugar lConseguido = lService.findById(lCreado.getId());
        assertNotNull(lConseguido);
        assertEquals(lConseguido, lCreado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);

        return lConseguido.getId();
    }

    @Test
    public void pruebasRestaurantes() {
        long idlugar = pruebaLugar();
        long idrestaurante = pruebaRestaurantes(idlugar);
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);
        pruebaResenhaRestaurante(ug, idrestaurante);
    }

    @Test
    public void pruebasMonitores(){
        Long idAsignatura = (long) 1;
        UsuarioGeneral ug = this.ugService.findByEmail(this.emailUsuario);
        UsuarioMonitor um = new UsuarioMonitor();
        
        Asignatura asig = pruebaAgregarAsignatura();

        um.setUsuario(ug);
        um.setAsignatura(asig);
        pruebaUsuarioMonitor(um, ug, asig);

    }

    public void pruebaUsuarioMonitor(UsuarioMonitor um, UsuarioGeneral ug, Asignatura asignatura){


        // se crea con el servicio
        UsuarioMonitor umCreado = this.umService.guardar(um);
        assertAll(
            () -> assertEquals(umCreado.getUsuario(), ug),
            () -> assertEquals(umCreado.getAsignatura(), asignatura)
            );

        // se busca el objeto
        UsuarioMonitor umConseguido = umService.findById(umCreado.getId());
        assertNotNull(umConseguido);
        assertEquals(umConseguido, umCreado);
        // GenericServiceTest.compareAllExceptId(a, aConseguida);
    }

    @Test
    public void pruebaTips() {

        WrapperTip wt = new WrapperTip();

        Long exigencia = (long) 1;
        Long idUsuario = this.ugService.findByEmail(this.emailUsuario).getId();
        Tip tip = new Tip();
        String descripcion = "descipcion";

		tip.setDescripcion(descripcion);

		wt.setExigencia(exigencia);
		wt.setTip(tip);
		wt.setIdUsuario(idUsuario);

        // se crea con el servicio
        Tip tCreada = this.temService.create(wt);

        assertEquals(tCreada.getNivelExigencia(), exigencia);
        assertEquals(tCreada.getDescripcion(), tip.getDescripcion());
        assertEquals(tCreada.getUsuario().getId(), idUsuario);

        // se busca el objeto
        Tip aConseguida = temService.findById(tCreada.getId());
        assertNotNull(aConseguida);
        assertEquals(aConseguida, tCreada);
    }

}
