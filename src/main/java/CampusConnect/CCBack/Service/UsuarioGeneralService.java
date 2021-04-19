package CampusConnect.CCBack.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import CampusConnect.CCBack.Model.GrupoEstudiantil;
import CampusConnect.CCBack.Model.InformacionUsuario;
import CampusConnect.CCBack.Model.ResenhaGrupoEstudiantil;
import CampusConnect.CCBack.Model.ResenhaRestaurante;
import CampusConnect.CCBack.Model.Restaurante;
import CampusConnect.CCBack.Model.Rol;
import CampusConnect.CCBack.Model.TipoAprendizaje;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;
import CampusConnect.CCBack.Wrappers.WrapperLogin;
import CampusConnect.CCBack.Wrappers.WrapperUsuarioGeneral;

@RestController
public class UsuarioGeneralService implements UserDetailsService {

    @Autowired
    private UsuarioGeneralRepository repository;

    @Autowired
    private RestaurantesService rService;

    @Autowired
    private TipoAprendizajeService taService;

    @Autowired
    private GruposEstudiantilesService geService;

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
        final ResenhaGrupoEstudiantil foroData,
        final Long idRestaurante
        ) {
        ResenhaGrupoEstudiantil rr = new ResenhaGrupoEstudiantil();
        UsuarioGeneral ug = this.findByEmail(email);
        GrupoEstudiantil restaurante = geService.findById(idRestaurante);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setGrupoEstudiantil(restaurante);
        rr.setUsuario(ug);
        ug.agregarResenhaGrupoEstudiantil(rr);
        return repository.save(ug);
    }

    public UsuarioGeneral crearResentaRestaurante(
        final String email,
        final ResenhaRestaurante foroData,
        final Long idRestaurante
        ) {
        ResenhaRestaurante rr = new ResenhaRestaurante();
        UsuarioGeneral ug = repository.findByEmail(email);
        Restaurante restaurante = rService.findById(idRestaurante);
        rr.setEstrellas(foroData.getEstrellas());
        rr.setRestaurante(restaurante);
        rr.setUsuario(ug);
        ug.agregarResenhaRestaurante(rr);
        return repository.save(ug);
    }

    public InformacionUsuario informacionUsuario(Long id) {
        return repository.findById(id).get().getInformacionUsuario();
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
        final String email,
        final Long idTipoAprendizaje
    ){
        UsuarioGeneral ug = repository.findByEmail(email);
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

}
