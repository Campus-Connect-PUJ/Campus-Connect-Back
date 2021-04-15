package CampusConnect.CCBack.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import CampusConnect.CCBack.Repository.RestauranteRepository;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

@RestController
@RequestMapping("/usuario")
class UsuarioGeneralService {

    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private RestauranteRepository restauranteRepo;

    @Autowired
    private GrupoEstudiantilRepository grupoEstudiantilRepo;

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

    @PostMapping("{id}/resenha_grupo_estudiantil/{id_res}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @RequestBody final ResenhaGrupoEstudiantil foroData,
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        UsuarioGeneral ug = repository.findById(idUsuario).get();
        GrupoEstudiantil restaurante = grupoEstudiantilRepo.findById(idRestaurante).get();
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
        Restaurante restaurante = restauranteRepo.findById(idRestaurante).get();
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
        return repository.findById(id).get().getEstiloAprendizaje();
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
    public UsuarioGeneral create(@RequestBody final UsuarioGeneral data) {
        UsuarioGeneral ug = new UsuarioGeneral();
        ug.setEmail(data.getEmail());
        ug.setNombre(data.getNombre());
        ug.setSemestre(data.getSemestre());
        return repository.save(ug);
    }

}
