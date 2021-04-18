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

    private UsuarioGeneral admin;

	public PasswordEncoder passwordEncoder;

	public RESTUserDetailsService() {
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

    @Override
    public UsuarioGeneral loadUserByUsername(final String username)
        throws UsernameNotFoundException {

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
            // User
            // .withUsername(ul.getEmail())
            // .password(ul.getPassword())
            // .roles(ul.getRol())
            // .build();
	}

}
