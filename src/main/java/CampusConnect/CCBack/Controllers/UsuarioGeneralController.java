package CampusConnect.CCBack.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.RolAdministrador;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioCAE;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Security.RESTAuthenticationProvider;
import CampusConnect.CCBack.Security.SecurityConstants;
import CampusConnect.CCBack.Service.UsuarioGeneralService;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
@RequestMapping("/usuario")
class UsuarioGeneralController {

    @Autowired
    private UsuarioGeneralService repository;

    @Autowired
    private RESTAuthenticationProvider restap;

    @GetMapping("all")
    public Iterable<UsuarioGeneral> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public UsuarioGeneral findById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @PostMapping("{id}/resenha_grupo_estudiantil/{id_res}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @RequestBody final ResenhaGrupoEstudiantil foroData,
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante
        ) {

            return repository.crearResenhaGrupoEstudiantil(
                foroData, idUsuario, idRestaurante);
    }

    @PostMapping("{id}/resenha_restaurante/{id_res}")
    public UsuarioGeneral crearResentaRestaurante(
        @RequestBody final ResenhaRestaurante foroData,
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_res") final Long idRestaurante
        ) {
            return repository.crearResentaRestaurante(
                foroData, idUsuario, idRestaurante);
    }

    @GetMapping("{id}/respuestas_foros")
    public List<RespuestaForo> respuestasForosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getRespuestasForo();
    }

    @GetMapping("{id}/foros")
    public List<Foro> postsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getForos();
    }

    @GetMapping("{id}/estilos_aprendizaje")
    public List<TipoAprendizaje> estilosAprendizajeUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getEstilosAprendizaje();
    }

    @GetMapping("{id}/roles_cae")
    public List<UsuarioCAE> rolesCAEUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getRolesCAE();
    }

    @GetMapping("{id}/monitorias")
    public List<Asignatura> monitoriasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getMonitorDe();
    }

    @GetMapping("{id}/caracteristicas")
    public List<Caracteristica> caracteristicasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getCaracteristicas();
    }

    @GetMapping("{id}/informacion")
    public InformacionUsuario informacionUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getInformacionUsuario();
    }

    @GetMapping("{id}/tips")
    public List<Tip> tipsUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getTips();
    }

    @GetMapping("{id}/tips_gustados")
    public List<Tip> tipsGustadosUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getTipsGustados();
    }

    @GetMapping("{id}/roles_admin")
    public List<RolAdministrador> rolesAdministradorUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getRolesAdministrador();
    }

    @GetMapping("{id}/carreras")
    public List<Carrera> carrerasUsuario(@PathVariable("id") Long id) {
        return repository.findById(id).getCarrerasUsuario();
    }

    @PostMapping("{id}/agregarTipoAprendizaje/{id_tip}")
    public UsuarioGeneral agregarTipAprendizaje(
        @PathVariable("id") final Long idUsuario,
        @PathVariable("id_tip") final Long idTipoAprendizaje
    ){
        return repository.agregarTipAprendizaje(idUsuario, idTipoAprendizaje);
    }

    @PostMapping("login/registro")
    public UsuarioGeneral registro(
        @RequestBody final WrapperUsuarioGeneral data,
        HttpServletResponse response
        ) {

        System.out.println("creando usuario");
        UsuarioGeneral ug = repository.registro(data);
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
        final UsuarioGeneral user = repository.login(login);
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

}
