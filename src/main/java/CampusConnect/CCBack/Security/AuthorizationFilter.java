package CampusConnect.CCBack.Security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {

            String header = req.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
            if (header == null || !header.startsWith(SecurityConstants.TOKEN_BEARER_PREFIX)) {
                chain.doFilter(req, res);
                return;
            }
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        }catch (Exception e){
            // throw new GeneralException(ExceptionCodesEnum.ADDRESS_NAME_IN_USE,"Error de autenticacion");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET_KEY)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }

}
