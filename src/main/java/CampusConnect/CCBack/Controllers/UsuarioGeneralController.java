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

import CampusConnect.CCBack.Model.Caracteristica;
import CampusConnect.CCBack.Model.Carrera;
import CampusConnect.CCBack.Model.Foro;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.RespuestaForo;
import CampusConnect.CCBack.Model.Tip;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Model.UsuarioMonitor;
import CampusConnect.CCBack.Security.RESTAuthenticationProvider;
import CampusConnect.CCBack.Security.SecurityConstants;
import CampusConnect.CCBack.Service.UsuarioGeneralService;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperMonitoria;
import CampusConnect.CCBack.Wrappers.WrapperPersoGrupos;
import CampusConnect.CCBack.Wrappers.WrapperPersoRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperSugeGrupos;
import CampusConnect.CCBack.Wrappers.WrapperSugeRestaurantes;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
@RequestMapping("/usuario")
class UsuarioGeneralController {

    @Autowired
    private UsuarioGeneralService ugService;

    @Autowired
    private RESTAuthenticationProvider restap;

    @GetMapping("data")
    public UsuarioGeneral getData(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email);
    }

    @GetMapping("all")
    public Iterable<UsuarioGeneral> findAll() {
        return ugService.findAll();
    }

    @GetMapping("{id}")
    public UsuarioGeneral findById(@PathVariable("id") Long id) {
        return ugService.findById(id);
    }

    @PostMapping("resenha_grupo_estudiantil/{id_res}")
    public UsuarioGeneral crearResenhaGrupoEstudiantil(
        @AuthenticationPrincipal String email,
        @RequestBody final ResenhaGrupoEstudiantil foroData,
        @PathVariable("id_res") final Long idRestaurante
        ) {
            return ugService.crearResenhaGrupoEstudiantil(
                email, foroData, idRestaurante);
    }

    @PostMapping("resenha_restaurante/{id_res}")
    public UsuarioGeneral crearResentaRestaurante(
        @AuthenticationPrincipal String email,
        @RequestBody final ResenhaRestaurante foroData,
        @PathVariable("id_res") final Long idRestaurante
        ) {
            return ugService.crearResentaRestaurante(
                email, foroData, idRestaurante);
    }

    @GetMapping("respuestas_foros")
    public List<RespuestaForo> respuestasForosUsuario(
        @AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getRespuestasForo();
    }

    @GetMapping("foros")
    public List<Foro> postsUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getForos();
    }

    @GetMapping("estilos_aprendizaje")
    public List<TipoAprendizaje> estilosAprendizajeUsuario(
        @AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getEstilosAprendizaje();
    }

    @GetMapping("monitorias")
    public List<UsuarioMonitor> monitoriasUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getMonitorDe();
    }

    @GetMapping("caracteristicas")
    public List<Caracteristica> caracteristicasUsuario(
        @AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getCaracteristicas();
    }

    @GetMapping("informacion")
    public InformacionUsuario informacionUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getInformacionUsuario();
    }

    @GetMapping("tips")
    public List<Tip> tipsUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getTips();
    }

    @GetMapping("tips_gustados")
    public List<Tip> tipsGustadosUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getTipsGustados();
    }

    @GetMapping("carreras")
    public List<Carrera> carrerasUsuario(@AuthenticationPrincipal String email) {
        return ugService.findByEmail(email).getCarrerasUsuario();
    }

    @PostMapping("{idUsuario}/agregarTipoAprendizaje/{id_tip}")
    public UsuarioGeneral agregarTipAprendizaje(
        //@AuthenticationPrincipal String username,
        @PathVariable("id_tip") final Long idTipoAprendizaje,
        @PathVariable("idUsuario") final Long idUsuario
    ){
        return ugService.agregarTipAprendizaje(idUsuario, idTipoAprendizaje);
    }

    @PostMapping("login/registro")
    public UsuarioGeneral registro(
        @RequestBody final WrapperUsuarioGeneral data,
        HttpServletResponse response
        ) {

        System.out.println("creando usuario");
        UsuarioGeneral ug = ugService.registro(data);
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
        final UsuarioGeneral user = ugService.login(login);
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

    // TODO: verificar que el usuario que realizar el cambio ya tenga rol admin
    @GetMapping("rolAdmin/{id}")
    public UsuarioGeneral toggleRolAdmin(@PathVariable("id") Long id) {
        return ugService.toggleRolAdmin(id);
    }

    @GetMapping("rolMonitor/{id}")
    public UsuarioGeneral toggleRolMonitor(
        // @AuthenticationPrincipal UsuarioGeneral ug
        @PathVariable("id") Long id
        ) {
        return ugService.toggleRolMonitor(id);
    }

    @PutMapping("persoGrupos")
    public UsuarioGeneral persoGrupos(
        @RequestBody final WrapperPersoGrupos wpg,
        @AuthenticationPrincipal String email) {

        return ugService.persoGrupos(wpg, email);
    }

    @PutMapping("persoRestaurantes")
    public UsuarioGeneral persoRestaurantes(
        @RequestBody final WrapperPersoRestaurantes wpr,
        @AuthenticationPrincipal String email) {

        return ugService.persoRestaurantes(wpr, email);
    }

    // TODO: verificar admin
    @PostMapping("/rol/{idUsuario}/{rol}")
    private UsuarioGeneral cambiarRol(
        @PathVariable("idUsuario") final Long idUsuario,
        @PathVariable("rol") final Short idRol
    ){
        return ugService.cambiarRol(idUsuario, idRol);
    }


    @PostMapping("agregarMonitoria/{idUsuario}")
    public void agregarMonitoria(
        @RequestBody final WrapperMonitoria infoMonitoria,
        @PathVariable("idUsuario") final Long idUsuario
    ){
        UsuarioGeneral ug = this.findById(idUsuario);

        ugService.crearMonitoria(ug, infoMonitoria);

    }

    @PutMapping("/monitor/{idMonitor}/{calificacion}")
    public UsuarioMonitor votarMonitor(
        //@PathVariable("idUsuario") final Long idUsuario,
        @PathVariable("idMonitor") final Long idMonitor,
        @PathVariable("calificacion") final Long calificacion
    ){
        return ugService.votarMonitor(idMonitor, calificacion);

    }

    @GetMapping("/monitor/{idMonitor}")
    public List<UsuarioMonitor> obtenerHorarios(
        @PathVariable("idMonitor") final Long idMonitor
    ){
        System.out.println("El id del monitor " + idMonitor);
        return ugService.obtenerHorarios(idMonitor, 750);
    }

    @GetMapping("/monitor/{dias}/{idMonitor}")
    public List<UsuarioMonitor> obtenerHorarios(
        @PathVariable("idMonitor") final Long idMonitor,
        @PathVariable("dias") final Long dias
    ){
        System.out.println("El id del monitor " + idMonitor);
        return ugService.obtenerHorarios(idMonitor, dias);
    }

    /*
    @PostMapping("agregarMonitoria")
    public void agregarMonitoria(
        @AuthenticationPrincipal String email,
        @RequestBody final WrapperMonitoria infoMonitoria
    ){
        repository.agregarMonitoria(infoMonitoria, email);
    }
*/
    @GetMapping("monitores/all")
    public Iterable<UsuarioGeneral> findMonitores(
    ) {
        return ugService.findMonitores();
    }
    
    @PutMapping("sugeRestaurantes")
    public UsuarioGeneral sugeRestaurantes(
        @RequestBody final WrapperSugeRestaurantes wpr) {

        return ugService.RegistarRecomendacionRestaurantes(wpr);
    }

    @PutMapping("sugeGrupos")
    public UsuarioGeneral sugeGurpos(
        @RequestBody final WrapperSugeGrupos wpg){

        return ugService.RegistarRecomendacionGrupos(wpg);
    }
}
