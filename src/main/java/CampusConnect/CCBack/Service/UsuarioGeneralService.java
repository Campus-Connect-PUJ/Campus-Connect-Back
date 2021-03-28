package CampusConnect.CCBack.Service;

import CampusConnect.CCBack.Model.Asignatura;
import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.RolAdministrador;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioCAE;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsuarioGeneralService {
    @Autowired
    private UsuarioGeneralRepository repository;

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("/usuarios")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioGeneral findForoById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/pruebaUsuario")
    public UsuarioGeneral pruebaCreacionUsuario() {
        UsuarioGeneral ug = new UsuarioGeneral(
            "pruebaDaniel",
            "correo_prueba@prueba.com",
            11,
            null,
            null
            );

        // ug.setCaracteristicas();
        // ug.setCarrera("sistemas");
        // ug.setCorreo("estoEsUnCorreo@hola.com");
        // List<TipoAprendizaje> ta = new ArrayList<>();
        // ta.add(new TipoAprendizaje());
        // ug.setEstiloAprendizaje(ta);
        // ug.setNombre("pruebaDaniel");
        // ug.setSemestre(11);

        return repository.save(ug);
    }

    @GetMapping("/usuarios/{id}/respuestas_foros")
    public List<RespuestaForo> respuestasForosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRespuestasForo();
    }

    @GetMapping("/usuarios/{id}/foros")
    public List<Foro> postsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getForos();
    }

    @GetMapping("/usuarios/{id}/estilos_aprendizaje")
    public List<TipoAprendizaje> estilosAprendizajeUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getEstiloAprendizaje();
    }

    @GetMapping("/usuarios/{id}/roles_cae")
    public List<UsuarioCAE> rolesCAEUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRolesCAE();
    }

    @GetMapping("/usuarios/{id}/monitorias")
    public List<Asignatura> monitoriasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getMonitorDe();
    }

    @GetMapping("/usuarios/{id}/caracteristicas")
    public List<Caracteristica> caracteristicasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCaracteristicas();
    }

    @GetMapping("/usuarios/{id}/informacion")
    public InformacionUsuario informacionUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getInformacionUsuario();
    }

    @GetMapping("/usuarios/{id}/tips")
    public List<Tip> tipsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTips();
    }

    @GetMapping("/usuarios/{id}/tips_gustados")
    public List<Tip> tipsGustadosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getTipsGustados();
    }

    @GetMapping("/usuarios/{id}/roles_admin")
    public List<RolAdministrador> rolesAdministradorUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getRolesAdministrador();
    }

    @GetMapping("/usuarios/{id}/carreras")
    public List<Carrera> carrerasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).get().getCarrerasUsuario();
    }
}
