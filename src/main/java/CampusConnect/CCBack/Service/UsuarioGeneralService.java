package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.RolAdministrador;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioCAE;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.GrupoEstudiantilRepository;
import CampusConnect.CCBack.Repository.ResenhaGrupoRepository;
import CampusConnect.CCBack.Repository.ResenhaRestauranteRepository;
import CampusConnect.CCBack.Repository.RestauranteRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Wrappers.WrapperInformacionUsuario;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
@RequestMapping("/usuario")
class UsuarioGeneralService {

    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private RestaurantesService rService;

    @Autowired
    private GruposEstudiantilesService geService;

    @Autowired
    private CarreraService cService;

    @Autowired
    private GrupoEstudiantilRepository regrupoEstudiantil;

    @Autowired
    private ResenhaGrupoRepository reregrupo;

    @Autowired
    private RestauranteRepository reRestaurante;

    @Autowired
    private ResenhaRestauranteRepository rereRestaurante;

	@Autowired
	public PasswordEncoder passwordEncoder;

    public UsuarioGeneral getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("all")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public UsuarioGeneral findById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("{id}/resenha_grupo_estudiantil/{id_res}/{calificacion}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante,
        @PathVariable("calificacion") final Long calificacion
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        GrupoEstudiantil grupoEstudiantil = geService.findById(idRestaurante);

        System.out.println("algo");

        rr.setEstrellas(calificacion);
        rr.setGrupoEstudiantil(grupoEstudiantil);
        rr.setUsuario(ug);
        ug.agregarResenhaGrupoEstudiantil(rr);
        grupoEstudiantil.agregarResenha(rr);

        System.out.println("Usuario "+ ug.getNombre() + " "+ ug.getResenhaGruposEstudiatiles().size());
        System.out.println("Grupo "+ grupoEstudiantil.getDescripcion() + " "+ grupoEstudiantil.getResenhas().size());


        //Reseña
        reregrupo.save(rr);
        //Grupo
        regrupoEstudiantil.save(grupoEstudiantil);
        //Usuario
        repository.save(ug);

        return ug;
    }

    @PostMapping("{id}/resenha_restaurante/{id_res}/{calificacion}")
    public UsuarioGeneral crearResentaRestaurante(
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante,
        @PathVariable("calificacion") final Long calificacion
        ) {
        ResenhaRestaurante rr = new ResenhaRestaurante();
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        Restaurante restaurante = rService.findById(idRestaurante);
        rr.setEstrellas(calificacion);
        rr.setRestaurante(restaurante);
        rr.setUsuario(ug);

        ug.agregarResenhaRestaurante(rr);
        restaurante.agregarResenha(rr);


        //Reseña
        rereRestaurante.save(rr);
        //Restaurante
        reRestaurante.save(restaurante);
        //Usuario
        repository.save(ug);
        

        return ug;
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

    @PostMapping
    public UsuarioGeneral create(@RequestBody final WrapperUsuarioGeneral data) {
        System.out.println("creando usuario");

        UsuarioGeneral ug = new UsuarioGeneral(
            data.getEmail(),
            passwordEncoder.encode(data.getPassword()),
            data.getNombre(),
            data.getApellido()
            );
        ug.setSemestre(data.getSemestre());
        return repository.save(ug);
    }

    // @PostMapping
    // public UsuarioGeneral formulario1(@RequestBody final UsuarioGeneral data) {
    //     UsuarioGeneral ug = new UsuarioGeneral(
    //         data.getEmail(),
    //         passwordEncoder.encode(data.getPassword()),
    //         data.getNombre(),
    //         data.getApellido()
    //         );
    //     ug.setSemestre(data.getSemestre());
    //     return repository.save(ug);
    // }

}
