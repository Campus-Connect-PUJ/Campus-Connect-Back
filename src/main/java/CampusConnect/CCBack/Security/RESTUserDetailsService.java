package CampusConnect.CCBack.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Rol;
import CampusConnect.CCBack.Model.UsuarioGeneral;
import CampusConnect.CCBack.Repository.UsuarioGeneralRepository;

@Service
public class RESTUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioGeneralRepository repository;

    private String adminUser;
    private String adminPass;
    private String adminRole;

	public PasswordEncoder passwordEncoder;

	public RESTUserDetailsService() {
		super();

        this.passwordEncoder = new BCryptPasswordEncoder();

        adminUser = "admin";
        adminPass = passwordEncoder.encode("admin"); // TODO: poner una mejor clave aca
        adminRole = Rol.string(Rol.ADMIN);

	}

    @Override
    public UserDetails loadUserByUsername(final String username)
        throws UsernameNotFoundException {

        // admin nunca queda guardado en bd
        System.out.println("------------------------------");
        System.out.println(username);

        if (username.equals(adminUser)) {
            System.out.println("es admin");

            return User
                .withUsername(adminUser)
                .password(adminPass)
                .roles(adminRole)
                .build();
        }

        System.out.println("*** Retrieving user");
        UsuarioGeneral ul = repository.findByEmail(username);
        if (ul == null) {
            throw new UsernameNotFoundException(
                "User Not Found with -> username or email: " + username
            );
        }
		return User
            .withUsername(ul.getEmail())
            .password(ul.getPassword())
            .roles(ul.getRol())
            .build();
	}

}
