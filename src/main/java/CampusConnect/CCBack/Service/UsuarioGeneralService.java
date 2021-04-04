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
import CampusConnect.CCBack.Model.UserCreationDetails;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Repository.CarreraRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class UsuarioGeneralService {
    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private CarreraRepository carrera_repository;

    // esto probablemente sea mejor quitarlo, pero puede ser util para pruebas
    @GetMapping("/usuario/all")
    public Iterable<UsuarioGeneral> findAllForos() {
        return repository.findAll();
    }

    @GetMapping("/usuario/id/{id}")
    public UsuarioGeneral findUsuarioById(@PathVariable("id") Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/usuario/email/{email}")
    public UsuarioGeneral findUsuarioByEmail(@PathVariable("email") String email) {
        Iterable<UsuarioGeneral> usuarios = repository.findAll();
        for (UsuarioGeneral usuarioGeneral : usuarios) {
            if (usuarioGeneral.getCorreo().equals(email)) {
                return usuarioGeneral;
            }
        }
        return null;
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

    @PostMapping("/createusuario")
    public String createUsuarioGeneral(@RequestBody UserCreationDetails userCreationDetails){
        UsuarioGeneral ug = new UsuarioGeneral();

        // ug.setCarrera("sistemas");
        
        // List<TipoAprendizaje> ta = new ArrayList<>();
        // ta.add(new TipoAprendizaje());
        // ug.setEstiloAprendizaje(ta);
        ug.setNombre(userCreationDetails.getName()+" "+userCreationDetails.getLast_name());
        ug.setCorreo(userCreationDetails.getEmail());
        ug.setSemestre(userCreationDetails.getSemestre_seleccionado());

        String identidadGenero = userCreationDetails.getGenero();
        String raza = userCreationDetails.getEtnico();
        String lugarOrigen = userCreationDetails.getNacimiento();
        Date fechaNacimiento = userCreationDetails.getMyDate();
        String religion = userCreationDetails.getReligion();
        String sexo = userCreationDetails.getSexo();
        
        List<String> carreras_seleccionadas = userCreationDetails.getCarreras_seleccionadas();
        Iterable<Carrera> carreras = this.carrera_repository.findAll();
        List<Carrera> carreras_seleccionadas_obj = new ArrayList<Carrera>();
        for(String carrera_sel : carreras_seleccionadas){
            for(Carrera carrera: carreras){
                if(carrera.getNombre().equals(carrera_sel)){
                    carreras_seleccionadas_obj.add(carrera);
                }
            }
        }
        ug.setCarrerasUsuario(carreras_seleccionadas_obj);

        InformacionUsuario infoUsuario = new InformacionUsuario(identidadGenero, raza, lugarOrigen, fechaNacimiento, religion, sexo, ug);
        ug.setInformacionUsuario(infoUsuario);
        repository.save(ug);


        return userCreationDetails.toString();
    }
}
