package CampusConnect.CCBack.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * https://www.baeldung.com/securing-a-restful-web-service-with-spring-security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private RESTAuthSuccessHandler authenticationSuccessHandler;

    @Autowired
    private RESTLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            // .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                    // Para mejorar seguridad, es recomendable activar
                    // CSRF, aunque eso requiere de varios cambios a
                    // la configuración actual
            .and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and().authorizeRequests()
                .antMatchers(
                    "/*" // los paths que empiezen desde / son disponibles para el publico
                    // aqui es necesario poner las urls las cuales son
                    // disponibles de manera publica, de momento puse
                    // * para hacer pruebas
                    )
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
            .and().logout()
                .logoutSuccessHandler(logoutSuccessHandler).and()
                .headers().frameOptions().disable();

        http.addFilterAfter(
            new AuthorizationFilter(),
            UsernamePasswordAuthenticationFilter.class
            );

    }

    /**
     * https://www.baeldung.com/spring-bean-annotations
     *
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        // ver https://stackoverflow.com/a/42053745
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
