package CampusConnect.CCBack.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import CampusConnect.CCBack.Wrappers.WrapperPersoGrupos;
import CampusConnect.CCBack.Wrappers.WrapperPersoRestaurantes;
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

    @PostMapping("resenha_grupo_estudiantil/{id_res}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @AuthenticationPrincipal String email,
        @RequestBody final ResenhaGrupoEstudiantil foroData,
        @PathVariable("id_res") final Long idRestaurante
        ) {
            return repository.crearResenhaGrupoEstudiantil(
                email, foroData, idRestaurante);
    }

    @PostMapping("resenha_restaurante/{id_res}")
    public UsuarioGeneral crearResentaRestaurante(
        @AuthenticationPrincipal String email,
        @RequestBody final ResenhaRestaurante foroData,
        @PathVariable("id_res") final Long idRestaurante
        ) {
            return repository.crearResentaRestaurante(
                email, foroData, idRestaurante);
    }

    @GetMapping("respuestas_foros")
    public List<RespuestaForo> respuestasForosUsuario(
        @AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getRespuestasForo();
    }

    @GetMapping("foros")
    public List<Foro> postsUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getForos();
    }

    @GetMapping("estilos_aprendizaje")
    public List<TipoAprendizaje> estilosAprendizajeUsuario(
        @AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getEstilosAprendizaje();
    }

    @GetMapping("roles_cae")
    public List<UsuarioCAE> rolesCAEUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getRolesCAE();
    }

    @GetMapping("monitorias")
    public List<Asignatura> monitoriasUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getMonitorDe();
    }

    @GetMapping("caracteristicas")
    public List<Caracteristica> caracteristicasUsuario(
        @AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getCaracteristicas();
    }

    @GetMapping("informacion")
    public InformacionUsuario informacionUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getInformacionUsuario();
    }

    @GetMapping("tips")
    public List<Tip> tipsUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getTips();
    }

    @GetMapping("tips_gustados")
    public List<Tip> tipsGustadosUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getTipsGustados();
    }

    @GetMapping("roles_admin")
    public List<RolAdministrador> rolesAdministradorUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getRolesAdministrador();
    }

    @GetMapping("carreras")
    public List<Carrera> carrerasUsuario(@AuthenticationPrincipal String email) {
        return repository.findByEmail(email).getCarrerasUsuario();
    }

    @PostMapping("agregarTipoAprendizaje/{id_tip}")
    public UsuarioGeneral agregarTipAprendizaje(
        @AuthenticationPrincipal String username,
        @PathVariable("id_tip") final Long idTipoAprendizaje
    ){
        return repository.agregarTipAprendizaje(username, idTipoAprendizaje);
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

    // @PostMapping("printUser")
    // public void printUserInfo(@AuthenticationPrincipal String username) {
    //     System.out.println("usuario: " + username);
    // }

    @PutMapping("persoGrupos")
    public UsuarioGeneral persoGrupos(
        @RequestBody final WrapperPersoGrupos wpg,
        @AuthenticationPrincipal String email) {

        return repository.persoGrupos(wpg, email);
    }


    @PutMapping("persoRestaurantes")
    public UsuarioGeneral persoRestaurantes(
        @RequestBody final WrapperPersoRestaurantes wpr,
        @AuthenticationPrincipal String email) {

        return repository.persoRestaurantes(wpr, email);
    }

}