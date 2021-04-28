package CampusConnect.CCBack.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Hobby;
import CampusConnect.CCBack.Model.Horario;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.Rol;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Repository.AsignaturaRepository;
import CampusConnect.CCBack.Repository.HorarioRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Repository.UsuarioMonitorRepository;
import CampusConnect.CCBack.Security.RESTAuthenticationProvider;
import CampusConnect.CCBack.Security.SecurityConstants;
import CampusConnect.CCBack.Wrappers.WrapperHorario;

import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperMonitoria;
import CampusConnect.CCBack.Wrappers.WrapperPersoGrupos;
import CampusConnect.CCBack.Wrappers.WrapperPersoRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;
import CampusConnect.CCBack.Wrappers.WrapperSugeRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperSugeGrupos;

@RestController
public class UsuarioGeneralService implements UserDetailsService {

    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private TipoAprendizajeService taService;

    @Autowired
    private CaracteristicasService cService;

    @Autowired
    private TipoComidaService tcService;

    @Autowired
    private AsignaturaService asService;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private UsuarioMonitorRepository monitorRepository;

    @Autowired
    private ActividadService aService;

    @Autowired
    private HobbyService hService;

    @Autowired
    private RegimenAlimenticioService regService;

    @Autowired
    private RegimenAlimenticioUsuarioService rauService;

    @Autowired
    private ResenhaGrupoEstudiantilService rgeService;

    @Autowired
    private ResenhaRestauranteService rrService;

	@Autowired
	public PasswordEncoder passwordEncoder;

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private RestaurantesService rService;

    private UsuarioGeneral admin;

	public UsuarioGeneralService() {
		super();

        this.passwordEncoder = new BCryptPasswordEncoder();

        this.admin = new UsuarioGeneral(
            "campusconnect2021@gmail.com",
            passwordEncoder.encode("admin"),
            "admin",
            "admin"
            );
            admin.setRol(Rol.ADMIN);
    }

    public void guardarUsuario(UsuarioGeneral ug){
        repository.save(ug);
    }

    public Iterable<UsuarioGeneral> findAll() {
        return repository.findAll();
    }

    public UsuarioGeneral findById(Long id) {
        return repository.findById(id).get();
    }

    public UsuarioGeneral findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        final String email,
        final ResenhaGrupoEstudiantil data,
        final Long idRestaurante
        ) {
        UsuarioGeneral ug = this.findByEmail(email);
        ResenhaGrupoEstudiantil rr = rgeService.create(ug, data, idRestaurante);
        ug.agregarResenhaGrupoEstudiantil(rr);
        return repository.save(ug);
    }

    public UsuarioGeneral crearResentaRestaurante(
        final String email,
        final ResenhaRestaurante data,
        final Long idRestaurante
        ) {
        UsuarioGeneral ug = repository.findByEmail(email);
        ResenhaRestaurante rr = rrService.create(ug, data, idRestaurante);
        ug.agregarResenhaRestaurante(rr);
        return repository.save(ug);
    }

    public UsuarioGeneral create(final WrapperUsuarioGeneral data) {
        UsuarioGeneral ug = new UsuarioGeneral(
            data.getEmail(),
            passwordEncoder.encode(data.getPassword()),
            data.getNombre(),
            data.getApellido()
            );
        ug.setSemestre(data.getSemestre());
        return repository.save(ug);
    }

    public UsuarioGeneral agregarTipAprendizaje(
        String email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = repository.findByEmail(email);
        List<TipoAprendizaje> tiposAprendizaje = ug.getEstilosAprendizaje();
        TipoAprendizaje ta = taService.findById(idTipoAprendizaje);

    
        if( !tiposAprendizaje.contains(ta) ){
            tiposAprendizaje.add(ta);
            ug.setEstilosAprendizaje(tiposAprendizaje);
        }

        return repository.save(ug);
    }

    public UsuarioGeneral borrarTipoAprendizaje(
        String email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = repository.findByEmail(email);
        List<TipoAprendizaje> tiposAprendizaje = ug.getEstilosAprendizaje();
        TipoAprendizaje ta = taService.findById(idTipoAprendizaje);
        
        if(tiposAprendizaje.contains(ta)){
            tiposAprendizaje.remove(ta);
            ug.setEstilosAprendizaje(tiposAprendizaje);
        }

        return repository.save(ug);
    }

    public UsuarioGeneral registro(final WrapperUsuarioGeneral data) {
        final UsuarioGeneral ug = this.create(data);
        return ug;
    }

    public UsuarioGeneral login(final WrapperLogin login) {
		final UsuarioGeneral user = this.loadUserByUsername(login.getUsername());
        return user;
    }

    @Override
    public UsuarioGeneral loadUserByUsername(final String username)
        throws UsernameNotFoundException {

        if (username.length() == 0) {
            throw new UsernameNotFoundException("usuario vacio");
        }

        // admin nunca queda guardado en bd
        System.out.println("------------------------------");
        System.out.println(username + " == " + this.admin.getUsername());

        if (username.equals(this.admin.getUsername())) {
            System.out.println("es admin");
            return this.admin;
        }

        System.out.println("*** Retrieving user");
        UsuarioGeneral ul = repository.findByEmail(username);
        if (ul == null) {
            throw new UsernameNotFoundException(
                "User Not Found with -> username or email: " + username
            );
        }
        return ul;
	}

    public UsuarioGeneral toggleRolAdmin(Long id) {
        UsuarioGeneral ug = this.findById(id);
        if (ug.getRoles().contains(Rol.string(Rol.ADMIN))) {
            ug.setRol(Rol.ADMIN);
        } else {
            ug.removeRol(Rol.ADMIN);
        }
        return repository.save(ug);
    }

    public UsuarioGeneral toggleRolMonitor(
        // @AuthenticationPrincipal UsuarioGeneral ug
        Long id
        ) {
        UsuarioGeneral ug = this.findById(id);
        System.out.println(ug.getEmail());

        if (ug.getRoles().contains(Rol.string(Rol.MONITOR))) {
            ug.setRol(Rol.MONITOR);
        } else {
            ug.removeRol(Rol.MONITOR);
        }
        return repository.save(ug);
    }
  
    // Una lista de características temáticas, string actividades
    // hobbies u el bool de si cree el Dios
    public UsuarioGeneral persoGrupos(
        final WrapperPersoGrupos wpg,
        String email
        ) {

        UsuarioGeneral ug = this.findByEmail(email);

        InformacionUsuario iu = ug.getInformacionUsuario();

        ug.reinicioPersoGrupos();
        iu.reinicioPersoGrupos();

        for (Long id: wpg.getCaracteristicas()) {
            Caracteristica c = cService.findById(id);
            if(!ug.getCaracteristicas().contains(c)){
                ug.agregarCaracteristica(c);
            }   
        }

        for (String nombre : wpg.getActividades()) {
            Actividad a = aService.findByName(nombre);
            if(a!=null){
                ug.agregarActividadInteres(a);
            }else{
                aService.crear(nombre);
                a = aService.findByName(nombre); 
                ug.agregarActividadInteres(a);
                aService.agregarUsuario(a.getId(), ug);
            }
        }

        for (String nombre : wpg.getHobbies()) {
            Hobby h = hService.findByName(nombre);
            if (h!=null){
               iu.agregarHobby(h); 
            }else{
                hService.crear(nombre);
                h = hService.findByName(nombre);
                iu.agregarHobby(h); 
            }
        }

        return repository.save(ug);
    }

    public UsuarioGeneral cambiarRol(
        final Long idUsuario,
        final Short idRol
    ){
        UsuarioGeneral ug = this.findById(idUsuario);
        if (!Rol.contain(idRol)) {
            return null;
        }
        if (ug.getRoles().contains(Rol.string(idRol))) {
            ug.setRol(idRol);
        } else {
            ug.removeRol(idRol);
        }
        return repository.save(ug);
    }


    public Iterable<UsuarioGeneral> findMonitores() {
        ArrayList<UsuarioGeneral> monitores = new ArrayList<>();
        ArrayList<UsuarioGeneral> todos = new ArrayList<>();

        todos = (ArrayList<UsuarioGeneral>) repository.findAll();

        for(int i=0; i<todos.size(); i++){
            if(todos.get(i).getMonitorDe().size()>0){
                monitores.add(todos.get(i));
            }
        }
        

        return monitores;
    }


    public boolean existeMonitoria(UsuarioGeneral ug, WrapperMonitoria infoMonitoria){
        boolean existe = false;

        for(int i=0; i<ug.getMonitorDe().size(); i++){
            if(ug.getMonitorDe().get(i).getAsignatura().getId() == infoMonitoria.idAsignatura){
                existe = true;
            }
        }


        return existe;
    }

    public UsuarioMonitor crearMonitoria(UsuarioGeneral ug, WrapperMonitoria infoMonitoria){
        
        UsuarioMonitor monitoria = new UsuarioMonitor();
        List<UsuarioMonitor> anterioresMonitorias = ug.getMonitorDe();
        Asignatura asignatura = asService.findById(infoMonitoria.idAsignatura);

        if(!existeMonitoria(ug, infoMonitoria)){
            monitoria.setAsignatura(asignatura);
            monitoria.setCalificacion(Long.valueOf(5));
            monitoria.setCantidadVotos(Long.valueOf(1));
            monitoria.setUsuario(ug);
    
            asignatura.addMonitor(monitoria);
            ug.addMonitorDe(monitoria);
    
            repository.save(ug);
            asignaturaRepository.save(asignatura);
            monitorRepository.save(monitoria);
        }

        return monitoria;
    }


    public Horario agregarHorariosMonitoria(UsuarioGeneral ug, WrapperHorario wpH){
        Horario horario = new Horario();
        List<UsuarioMonitor> anterioresMonitorias = ug.getMonitorDe();
        UsuarioMonitor monitoria = new UsuarioMonitor();
        boolean yaexiste = false;
        System.out.println("->"+wpH.fechaInicio + " - "+ wpH.fechaFin);
        horario.setFechaInicial(wpH.getFechaInicial());
        horario.setFechaFinal(wpH.getFechaFinal());
        horario.setfechaInicio(wpH.fechaInicio);
        horario.setfechaFin(wpH.fechaFin);
        
        for(int i=0; i<anterioresMonitorias.size(); i++){

            for(int j=0; j<anterioresMonitorias.get(i).getHorarios().size(); j++){


                //String str = "2016-03-04 11:30"; 
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
                //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


                LocalDate localDateGuardadoDia = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                LocalTime tiempoGuardado = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalTime();
                
                
                LocalDate localDate = horario.getFechaInicial().toLocalDate();
                LocalTime tiempoNuevo = horario.getFechaInicial().toLocalTime();

                if(localDateGuardadoDia.isEqual(localDate) && (tiempoGuardado.getHour() == tiempoNuevo.getHour() && tiempoGuardado.getMinute() == tiempoNuevo.getMinute())){
                    yaexiste = true;
                }
            }   

            if(anterioresMonitorias.get(i).getAsignatura().getId() == wpH.getIdAsignatura()){
                monitoria = anterioresMonitorias.get(i);
            }
        }

        if(!yaexiste){
            monitoria.addHorario(horario);
            ug.addMonitorDe(monitoria);
            horario.setMonitor(monitoria);

            repository.save(ug);
            monitorRepository.save(monitoria);
            horarioRepository.save(horario);
        }


        return horario; 
    }




    //Un regimenen alimenticio, nivel de exigencia, lista de comidas favoritas, una ambientación
    public UsuarioGeneral persoRestaurantes(
        final WrapperPersoRestaurantes wpr,
        String email
        ) {

        UsuarioGeneral ug = this.findByEmail(email);

        ug.reinicioPersoRestaurantes();

        Long idReg = wpr.getRegimenAlimenticio();
        int nivelExigencia = wpr.getNivelExigencia();
        RegimenAlimenticio regimen = regService.findById(idReg);
        
        if(ug.getRegimenAlimenticio()==null){
            RegimenAlimenticioUsuario regimenUsuario = rauService.create(
                regimen, nivelExigencia, ug
            );
            ug.setRegimenAlimenticio(regimenUsuario);
        }

        String ambientacion = wpr.getAmbientacion();
        ug.setAmbientacion(ambientacion);
        for(Long id: wpr.getComidas()){
            TipoComida comida = tcService.findById(id);
            ug.agregarComida(comida);
        }

        return repository.save(ug);
    }


    public UsuarioMonitor votarMonitor(long idMonitor, long calificacion){
        UsuarioMonitor um = monitorRepository.findById(idMonitor).get();

        um.setCalificacion(um.getCalificacion() + calificacion);
        um.setCantidadVotos(um.getCantidadVotos()+1);
        
        return monitorRepository.save(um);
    }

    public List<UsuarioMonitor> obtenerHorarios(long idMonitor, long dias){
        List<UsuarioMonitor> monitores = new ArrayList<>();

        UsuarioGeneral ug = repository.findById(idMonitor).get();
        List<UsuarioMonitor> todasLasMonitorias = ug.getMonitorDe();
        //monitores = todasLasMonitorias;

        LocalDate hoy = LocalDate.now();
        LocalDate despues = hoy.plusDays(dias);
        //hoy = hoy.plusDays(15);

        
        for(int i=0; i<todasLasMonitorias.size(); i++){
            for(int j=0; j<todasLasMonitorias.get(i).getHorarios().size(); j++){
                LocalDate localDate = todasLasMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                
                if( (localDate.isAfter(hoy) || localDate.equals(hoy)) && localDate.isBefore(despues) && !monitores.contains(todasLasMonitorias.get(i)) ){
                    monitores.add(todasLasMonitorias.get(i));
                }
            }  
        }

        return monitores;
    }

    public UsuarioGeneral RegistarRecomendacionGrupos(final WrapperSugeGrupos wsg){
        UsuarioGeneral ug = this.findById(wsg.getIdUsuario());

        for (Long id: wsg.getIdsGrupos()){
            GrupoEstudiantil g = geService.findById(id);
            ug.agregarGRupoReco(g);
        }

        return repository.save(ug);
    }

    public UsuarioGeneral RegistarRecomendacionRestaurantes(final WrapperSugeRestaurantes wsr){
        UsuarioGeneral ug = this.findById(wsr.getIdUsuario());

        for (Long id: wsr.getIdsRestaurantes()){
            Restaurante r = rService.findById(id);
            ug.agregarRestauranteReco(r);
        }

        return repository.save(ug);
    }
}
