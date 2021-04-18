package CampusConnect.CCBack.Security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

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

import CampusConnect.CCBack.Wrappers.WrapperLogin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class RESTAuthenticationProvider {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RESTUserDetailsService userDetailsService;

	@Autowired
	public PasswordEncoder passwordEncoder;

	// public Authentication authenticate(final WrapperLogin authentication)
    //     throws AuthenticationException {

    //     final String name = authentication.getUser();
    //     final String password = authentication.getPassword();

    //     // logger.info(
    //     //     "\n---------------------------------------------------\n" +
    //     //     " Name = " + name + " ,Password = " + password +
    //     //     "\n---------------------------------------------------\n"
    //     //     );

	// 	final UserDetails user = userDetailsService.loadUserByUsername(name);

	// 	if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	// 		logger.info("Succesful authentication!");
	// 		return new UsernamePasswordAuthenticationToken(
    //             user,
    //             user.getPassword(),
    //             user.getAuthorities()
    //             );
	// 	} else {
	// 		logger.info("Login fail!");
	// 		return null;
	// 	}
	// }

	public String authenticateToken(
		final UserDetails user,
        final WrapperLogin authentication
        )
        throws AuthenticationException {

        final String name = authentication.getUsername();
        final String password = authentication.getPassword();

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			logger.info("Succesful authentication!");
            String token = Jwts.builder()
                .setIssuedAt(new Date()).setIssuer(SecurityConstants.ISSUER_INFO)
                .setSubject(name)
                .setExpiration(
                    new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME)
                    )
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
            return token;
		} else {
			logger.info("Login fail!");
			return null;
		}
	}

    // public String createToken(Authentication auth) throws IOException, ServletException {

    //     String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
    //             .setSubject(((UsuarioGeneral)auth.getPrincipal()).getUsername())
    //             .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
    //             .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
    //     return token;
    // }


}
