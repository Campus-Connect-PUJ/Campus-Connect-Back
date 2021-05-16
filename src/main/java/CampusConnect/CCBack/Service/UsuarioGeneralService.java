package CampusConnect.CCBack.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Hobby;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Rol;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.TipoComida;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperMonitoria;
import CampusConnect.CCBack.Wrappers.WrapperPersoGrupos;
import CampusConnect.CCBack.Wrappers.WrapperPersoRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@Service
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
    private UsuarioMonitorService umService;

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
        return GenericService.findAll(repository);
    }

    public UsuarioGeneral findById(Long id) {
        return GenericService.findById(repository, id);
    }

    public UsuarioGeneral findByEmail(String email) {
        try {
            UsuarioGeneral ul = repository.findByEmail(email);
            if (ul == null) {
                // throw new UsernameNotFoundException("Usuario con correo " + email + " no encontrado");
                throw new Exception();
            }
            return ul;
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario con correo " + email + " no encontrado", exc);
        }
    }

    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        final String email,
        final ResenhaGrupoEstudiantil data,
        final Long idRestaurante
        ) {
        UsuarioGeneral ug = this.findByEmail(email);
        ResenhaGrupoEstudiantil rr = rgeService.create(ug, data, idRestaurante);
        ug.agregarResenhaGrupoEstudiantil(rr);
        return GenericService.save(repository, ug);
    }

    public UsuarioGeneral crearResentaRestaurante(
        final String email,
        final ResenhaRestaurante data,
        final Long idRestaurante
        ) {
        UsuarioGeneral ug = this.findByEmail(email);
        ResenhaRestaurante rr = rrService.create(ug, data, idRestaurante);
        ug.agregarResenhaRestaurante(rr);
        return GenericService.save(repository, ug);
    }

    public UsuarioGeneral create(final WrapperUsuarioGeneral data) {
        UsuarioGeneral ug = new UsuarioGeneral(
            data.getEmail(),
            passwordEncoder.encode(data.getPassword()),
            data.getNombre(),
            data.getApellido()
            );
        ug.setSemestre(data.getSemestre());
        return GenericService.create(repository, ug);
    }

    public UsuarioGeneral agregarTipAprendizaje(
        String email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = this.findByEmail(email);
        List<TipoAprendizaje> tiposAprendizaje = ug.getEstilosAprendizaje();
        TipoAprendizaje ta = taService.findById(idTipoAprendizaje);


        if( !tiposAprendizaje.contains(ta) ){
            tiposAprendizaje.add(ta);
            ug.setEstilosAprendizaje(tiposAprendizaje);
        }

        return GenericService.save(repository, ug);
    }

    public UsuarioGeneral borrarTipoAprendizaje(
        String email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = this.findByEmail(email);
        List<TipoAprendizaje> tiposAprendizaje = ug.getEstilosAprendizaje();
        TipoAprendizaje ta = taService.findById(idTipoAprendizaje);

        if(tiposAprendizaje.contains(ta)){
            tiposAprendizaje.remove(ta);
            ug.setEstilosAprendizaje(tiposAprendizaje);
        }

        return GenericService.save(repository, ug);
    }

    public UsuarioGeneral registro(final WrapperUsuarioGeneral data) {
        final UsuarioGeneral ug = this.create(data);
        return ug;
    }

    public UsuarioGeneral login(final WrapperLogin login) {
        return this.loadUserByUsername(login.getUsername());
    }

    @Override
    public UsuarioGeneral loadUserByUsername(final String username)
        throws UsernameNotFoundException {

        if (username.length() == 0) {
            throw new UsernameNotFoundException("usuario vacio");
        }

        // admin nunca queda guardado en bd

        if (username.equals(this.admin.getUsername())) {
            System.out.println("------------------------------");
            System.out.println(username + " == " + this.admin.getUsername());
            System.out.println("es admin");
            return this.admin;
        }

        UsuarioGeneral ul = this.findByEmail(username);
        System.out.println("*** Retrieving user");
        return ul;
	}

    public UsuarioGeneral toggleRolAdmin(Long id) {
        UsuarioGeneral ug = this.findById(id);
        if (ug.getRoles().contains(Rol.string(Rol.ADMIN))) {
            ug.setRol(Rol.ADMIN);
        } else {
            ug.removeRol(Rol.ADMIN);
        }
        return GenericService.save(repository, ug);
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
        return GenericService.save(repository, ug);
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
                aService.create(nombre);
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

        return GenericService.save(repository, ug);
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
        return GenericService.save(repository, ug);
    }

    public Iterable<UsuarioGeneral> findMonitores() {
        ArrayList<UsuarioGeneral> monitores = new ArrayList<>();
        ArrayList<UsuarioGeneral> todos = new ArrayList<>();

        todos = (ArrayList<UsuarioGeneral>) this.findAll();

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
        Asignatura asignatura = asService.findById(infoMonitoria.idAsignatura);

        if(!existeMonitoria(ug, infoMonitoria)){
            monitoria.setAsignatura(asignatura);
            monitoria.setCalificacion(Long.valueOf(5));
            monitoria.setCantidadVotos(Long.valueOf(1));
            monitoria.setUsuario(ug);

    
            asignatura.addMonitor(monitoria);
            ug.addMonitorDe(monitoria);
    
            GenericService.save(repository, ug);
            //repository.save(ug);

            //asService.create(asignatura);
            //asignaturaRepository.save(asignatura);
            umService.guardar(monitoria);
            //monitorRepository.save(monitoria);
        }

        return monitoria;
    }

/*
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
        horario.lugar = wpH.lugar;
        
        for(int i=0; i<anterioresMonitorias.size(); i++){
            for(int j=0; j<anterioresMonitorias.get(i).getHorarios().size(); j++){
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
            //monitoria.addHorario(horario);


            //asignatura.addMonitor(monitoria);

            //ug.addMonitorDe(monitoria);

            GenericService.save(repository, ug);
            asignaturaService.create(asignatura);
            umService.guardar(monitoria);
        }
        return horario; 
    }


    public void borrarHorarioMonitoria(UsuarioGeneral ug, WrapperHorario wpH){
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
                LocalDate localDateGuardadoDia = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalDate();
                LocalTime tiempoGuardado = anterioresMonitorias.get(i).getHorarios().get(j).getFechaInicial().toLocalTime();
                LocalDate localDate = horario.getFechaInicial().toLocalDate();
                LocalTime tiempoNuevo = horario.getFechaInicial().toLocalTime();
                if(localDateGuardadoDia.isEqual(localDate) && (tiempoGuardado.getHour() == tiempoNuevo.getHour() && tiempoGuardado.getMinute() == tiempoNuevo.getMinute())){
                    yaexiste = true;
                    horario = anterioresMonitorias.get(i).getHorarios().get(j);
                }
            }   
            if(anterioresMonitorias.get(i).getAsignatura().getId() == wpH.getIdAsignatura()){
                monitoria = anterioresMonitorias.get(i);
            }
        }
        if(yaexiste){
            monitoria.quitarHorario(horario);
            ug.quitarMonitorDe(monitoria);
            horarioRepository.delete(horario);

            if(monitoria.getHorarios().size()==0){
                monitorRepository.delete(monitoria);
            }
            else{
                monitorRepository.save(monitoria);
            }


            repository.save(ug);
            
        }

    }
*/

    // Un regimenen alimenticio, nivel de exigencia, lista de comidas
    // favoritas, una ambientación
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

        return GenericService.save(repository, ug);
    }

    public UsuarioMonitor votarMonitor(long idMonitor, long calificacion){
        UsuarioMonitor um = umService.findById(idMonitor);

        um.setCalificacion(um.getCalificacion() + calificacion);
        um.setCantidadVotos(um.getCantidadVotos()+1);

        return umService.guardar(um);
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

    public UsuarioGeneral guardarUsuario(UsuarioGeneral ug) {
        return GenericService.save(repository, ug);
    }

}
