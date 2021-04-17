package CampusConnect.CCBack.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RESTAuthenticationProvider implements AuthenticationProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RESTUserDetailsService userDetailsService;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();

        logger.info(
            "\n---------------------------------------------------\n" +
            " Name = " + name + " ,Password = " + password +
            "\n---------------------------------------------------\n"
            );

		final UserDetails user = userDetailsService.loadUserByUsername(name);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			logger.info("Succesful authentication!");
			return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		} else {
			logger.info("Login fail!");
			return null;
		}
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
