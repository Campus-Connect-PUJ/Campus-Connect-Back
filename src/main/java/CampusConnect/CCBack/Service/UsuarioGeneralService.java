package CampusConnect.CCBack.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.LocalTimeToDateConverter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;

import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Horario;
import CampusConnect.CCBack.Model.Hobby;
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
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperMonitoria;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
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
        final Long email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = repository.findById(email).get();
        List<TipoAprendizaje> tiposAprendizaje = new ArrayList<TipoAprendizaje>();
        tiposAprendizaje = ug.getEstilosAprendizaje();
        if(!tiposAprendizaje.contains(taService.findById(idTipoAprendizaje))){
            tiposAprendizaje.add(taService.findById(idTipoAprendizaje));
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

    public void agregarMonitoria(
        final WrapperMonitoria infoMonitoria,
        final String email
    ){
        UsuarioGeneral ug = this.findByEmail(email);

        List<UsuarioMonitor> monitorDe = ug.getMonitorDe();
        if(monitorDe.size() > 0 && existeMonitoria(ug, infoMonitoria)){
            //TODO: Agregar horarios a monitoria
        }
        else{
            //Crear desde 0 monitoria
            System.out.println("*********************************************");
            crearMonitoria(ug, infoMonitoria);
        }

        repository.save(ug);

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

    public UsuarioMonitor crearMonitoria(UsuarioGeneral ug, WrapperMonitoria infoMonitoria){

        UsuarioMonitor monitoria = new UsuarioMonitor();
        List<UsuarioMonitor> anterioresMonitorias = ug.getMonitorDe();
        Asignatura asignatura = asService.findById(Long.parseLong(infoMonitoria.asignatura));
        Horario horario = new Horario();

        horario.setFechaInicial(infoMonitoria.getFechaInicial());
        horario.setFechaFinal(infoMonitoria.getFechaFinal());
        
        System.out.println("Asginatura "+asignatura.getNombre());
        monitoria.setAsignatura(asignatura);
        monitoria.addHorario(horario);
        monitoria.setUsuario(ug);
        monitoria.setCalificacion(Long.valueOf(5));
        monitoria.setCantidadVotos(Long.valueOf(1));
        boolean yaexiste = false;
        boolean agregarHorario = false;
        int indice = 0;
        for(int i=0; i<anterioresMonitorias.size(); i++){
            LocalDate localDateGuardadoDia = anterioresMonitorias.get(i).getHorarios().get(0).getFechaInicial().toLocalDate();
            LocalTime tiempoGuardado = anterioresMonitorias.get(i).getHorarios().get(0).getFechaInicial().toLocalTime();
            
            LocalDate localDate = horario.getFechaInicial().toLocalDate();
            LocalTime tiempoNuevo = horario.getFechaInicial().toLocalTime();
            System.out.println(localDateGuardadoDia.isEqual(localDate) + " = " + localDateGuardadoDia + " " + tiempoGuardado);
            System.out.println(tiempoGuardado.equals(tiempoNuevo) + " = " + tiempoGuardado.getHour() + " " + tiempoNuevo.getHour() +  tiempoGuardado.getMinute() + " " + tiempoNuevo.getMinute());

            if(localDateGuardadoDia.isEqual(localDate) && (tiempoGuardado.getHour() == tiempoNuevo.getHour() && tiempoGuardado.getMinute() == tiempoNuevo.getMinute())){
                yaexiste = true;
            }
            else if(anterioresMonitorias.get(i).getAsignatura().getId() == asignatura.getId()){
                agregarHorario = true;
            }

            
        }

        if(!agregarHorario && !yaexiste){
            horario.setMonitor(monitoria);
            asignatura.addMonitor(monitoria);
            ug.addMonitorDe(monitoria);
    
            monitorRepository.save(monitoria);
            asignaturaRepository.save(asignatura);
            horarioRepository.save(horario);
            
            repository.save(ug);
        }

        if(agregarHorario && !yaexiste){
            monitoria = anterioresMonitorias.get(indice);

            monitoria.addHorario(horario);
            horario.setMonitor(monitoria);
    
            monitorRepository.save(monitoria);
            asignaturaRepository.save(asignatura);
            horarioRepository.save(horario);
            
            repository.save(ug);
        }



        return monitoria;
    }

    public boolean existeMonitoria(UsuarioGeneral ug, WrapperMonitoria infoMonitoria){

        return false;
    }

    //Un regimenen alimenticio, nivel de exigencia, lista de comidas favoritas, una ambientación
    public UsuarioGeneral persoRestaurantes(
        final WrapperPersoRestaurantes wpr,
        String email
        ) {

        UsuarioGeneral ug = this.findByEmail(email);

        InformacionUsuario iu = ug.getInformacionUsuario();

        Long idReg = wpr.getRegimenAlimenticio();
        Long nivelExigencia = wpr.getNivelExigencia();
        RegimenAlimenticio regimen = regService.findById(idReg);

        RegimenAlimenticioUsuario regimenUsuario = rauService.create(
            regimen, nivelExigencia.intValue(), ug
        );

        ug.setRegimenAlimenticio(regimenUsuario);

        String ambientacion = wpr.getAmbientacion();
        ug.setAmbientacion(ambientacion);
        for(Long id: wpr.getComidas()){
            TipoComida comida =tcService.findById(id);
            ug.agregarComida(comida);
        }

        return repository.save(ug);
    }


    public UsuarioMonitor votarMonitor(long idMonitor, long calificacion){
        UsuarioMonitor um = monitorRepository.findById(idMonitor).get();
        System.out.println("Calificiacion " + um.getCalificacion() + " " + calificacion + " " + um.getCantidadVotos());
        
        um.setCalificacion(um.getCalificacion() + calificacion);
        um.setCantidadVotos(um.getCantidadVotos()+1);
        
        return monitorRepository.save(um);
    }

    public List<UsuarioMonitor> obtenerHorarios(long idMonitor, long dias){
        List<UsuarioMonitor> monitores = new ArrayList<>();

        UsuarioGeneral ug = repository.findById(idMonitor).get();
        System.out.println("Cantidad de monitorias "+ ug.getMonitorDe().size());
        List<UsuarioMonitor> todasLasMonitorias = ug.getMonitorDe();
        //monitores = todasLasMonitorias;

        LocalDate hoy = LocalDate.now();
        LocalDate despues = hoy.plusDays(dias);
        //hoy = hoy.plusDays(15);

        
        for(int i=0; i<todasLasMonitorias.size(); i++){
            for(int j=0; j<todasLasMonitorias.get(i).getHorarios().size(); j++){
                LocalDate localDate = todasLasMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                System.out.println(localDate + " "+ hoy + " = " + localDate.isBefore(despues) + " " + despues);
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
