package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Actividad;
import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.Hobby;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.RegimenAlimenticio;
import CampusConnect.CCBack.Model.RegimenAlimenticioUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.RolAdministrador;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioCAE;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Security.RESTAuthenticationProvider;
import CampusConnect.CCBack.Security.RESTUserDetailsService;
import CampusConnect.CCBack.Security.SecurityConstants;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperPersoGrupos;
import CampusConnect.CCBack.Wrappers.WrapperPersoRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
@RequestMapping("/usuario")
class UsuarioGeneralService {

    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private RestaurantesService rService;

    @Autowired
    private RESTAuthenticationProvider restap;

    @Autowired
    private TipoAprendizajeService taService;

    @Autowired
    private CaracteristicasService cService;

    @Autowired
    private RESTUserDetailsService rudService;

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private ActividadService aService;

    @Autowired
    private HobbyService hService;

	@Autowired
	public PasswordEncoder passwordEncoder;

    @Autowired
    private RegimenAlimenticioService regService;

	public UsuarioGeneralService() {}

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("all")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public UsuarioGeneral findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("{id}/resenha_grupo_estudiantil/{id_res}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @RequestBody final ResenhaGrupoEstudiantil foroData,
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        GrupoEstudiantil restaurante = geService.findById(idRestaurante);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setGrupoEstudiantil(restaurante);
        rr.setUsuario(ug);
        ug.agregarResenhaGrupoEstudiantil(rr);
        return repository.save(ug);
    }

    @PostMapping("{id}/resenha_restaurante/{id_res}")
    public UsuarioGeneral crearResentaRestaurante(
        @RequestBody final ResenhaRestaurante foroData,
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante
        ) {
        ResenhaRestaurante rr = new ResenhaRestaurante();
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        Restaurante restaurante = rService.findById(idRestaurante);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setRestaurante(restaurante);
        rr.setUsuario(ug);
        ug.agregarResenhaRestaurante(rr);
        return repository.save(ug);
    }

    @GetMapping("{id}/respuestas_foros")
    public List<RespuestaForo> respuestasForosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRespuestasForo();
    }

    @GetMapping("{id}/foros")
    public List<Foro> postsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getForos();
    }

    @GetMapping("{id}/estilos_aprendizaje")
    public List<TipoAprendizaje> estilosAprendizajeUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getEstilosAprendizaje();
    }

    @GetMapping("{id}/roles_cae")
    public List<UsuarioCAE> rolesCAEUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRolesCAE();
    }

    @GetMapping("{id}/monitorias")
    public List<Asignatura> monitoriasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getMonitorDe();
    }

    @GetMapping("{id}/caracteristicas")
    public List<Caracteristica> caracteristicasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCaracteristicas();
    }

    @GetMapping("{id}/informacion")
    public InformacionUsuario informacionUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getInformacionUsuario();
    }

    @GetMapping("{id}/tips")
    public List<Tip> tipsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTips();
    }

    @GetMapping("{id}/tips_gustados")
    public List<Tip> tipsGustadosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTipsGustados();
    }

    @GetMapping("{id}/roles_admin")
    public List<RolAdministrador> rolesAdministradorUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRolesAdministrador();
    }

    @GetMapping("{id}/carreras")
    public List<Carrera> carrerasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCarrerasUsuario();
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

    @PostMapping("{id}/agregarTipoAprendizaje/{id_tip}")
    public UsuarioGeneral agregarTipAprendizaje(
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_tip") final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        List<TipoAprendizaje> tiposAprendizaje = new ArrayList<TipoAprendizaje>();
        tiposAprendizaje = ug.getEstilosAprendizaje();
        if(!tiposAprendizaje.contains(taService.findById(idTipoAprendizaje))){
            tiposAprendizaje.add(taService.findById(idTipoAprendizaje));
            ug.setEstilosAprendizaje(tiposAprendizaje);
        }
        

        return repository.save(ug);
    }

    @PostMapping("login/registro")
    public UsuarioGeneral registro(
        @RequestBody final WrapperUsuarioGeneral data,
        HttpServletResponse response
        ) {
        System.out.println("creando usuario");
        UsuarioGeneral ug = this.create(data);
        WrapperLogin wl = new WrapperLogin();
        wl.setUsername(data.getEmail());
        wl.setPassword(data.getPassword());
        return resp(wl, ug, response);
    }

    @PostMapping("login")
    public UsuarioGeneral login(
        @RequestBody final WrapperLogin login,
        HttpServletResponse response
        ) {
        System.out.println("login");
        System.out.println(login.getUsername());
        System.out.println(login.getPassword());
		final UsuarioGeneral user = rudService.loadUserByUsername(login.getUsername());
        return resp(login, user, response);
    }

    private UsuarioGeneral resp(
        @RequestBody final WrapperLogin login,
        UsuarioGeneral ug,
        HttpServletResponse response
        ) {
        String token = restap.authenticateToken(ug, login);

        response.addHeader(
            SecurityConstants.HEADER_AUTHORIZACION_KEY,
            SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");

        return ug;
    }

    // Una lista de características temáticas, string actividades
    // hobbies u el bool de si cree el Dios
    @PutMapping("persoGrupos")
    public UsuarioGeneral persoGrupos(
        @RequestBody final WrapperPersoGrupos wpg,
        @AuthenticationPrincipal UsuarioGeneral ug
        ) {

        InformacionUsuario iu = ug.getInformacionUsuario();

        for (Long id: wpg.getCaracteristicas()) {
            Caracteristica c = cService.findById(id);
            ug.agregarCaracteristica(c);
        }

        for (String nombre : wpg.getActividades()) {
            Actividad a = aService.findByName(nombre);
            if(a!=null){
                ug.agregarActividadInteres(a);
            }else{
                a = new Actividad();
                a.setNombre(nombre);
                ug.agregarActividadInteres(a);
            }
            
        }

        for (String nombre : wpg.getHobbies()) {
            Hobby h = hService.findByName(nombre);
            if (h!=null){
               iu.agregarHobby(h); 
            }else{
                h = new Hobby();
                h.setNombre(nombre);
                iu.agregarHobby(h); 
            }
        }

        return ug;
    }

    //Un regimenen alimenticio, nivel de exigencia, lista de comidas favoritas, una ambientación
    @PutMapping("persoRestaurantes")
    public UsuarioGeneral persoRestaurantes(
        @RequestBody final WrapperPersoRestaurantes wpr,
        @AuthenticationPrincipal UsuarioGeneral ug
        ) {

        InformacionUsuario iu = ug.getInformacionUsuario();

        Long idReg = wpr.getRegimenAlimenticio();
        Long nivelExigencia = wpr.getNivelExigencia();
        RegimenAlimenticio regimen = regService.findById(idReg);
        RegimenAlimenticioUsuario regimenUsuario = new RegimenAlimenticioUsuario();

        regimenUsuario.setExigencia(nivelExigencia);
        regimenUsuario.setRegimenAlimenticio(regimen);

        ug.setRegimenAlimenticio(regimenUsuario);

        String ambientacion = wpr.getAmbientacion();
        for(Long id: wpr.getComidas()){
            
        }

        
        return ug;
    }
}
