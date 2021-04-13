package CampusConnect.CCBack.Security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.UsuarioLogin;

@Service
public class RESTUserDetailsService implements UserDetailsService {
	Map<String, UsuarioLogin> users = new HashMap<>();

	public RESTUserDetailsService() {
		super();
		addUser("user", "user", "ROLE_USER");
		addUser("mod", "mod", "ROLE_MOD");
		addUser("admin", "admin", "ROLE_ADMIN");
	}

    public void addUser(final String user, final String password, final String auth) {
        users.put(user, new User(Long.valueOf(this.users.size()), user, password, auth));
    }

    @Override
    public UsuarioLogin loadUserByUsername(final String username) throws UsernameNotFoundException {
		// TODO En este método debería recuperarlse la info del usuario desde la base de datos

		System.out.println("*** Retrieving user");
		return users.get(username);
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(final Map<String, User> users) {
		this.users = users;
	}

}
