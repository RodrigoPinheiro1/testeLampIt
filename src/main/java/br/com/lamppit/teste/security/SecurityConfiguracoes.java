package br.com.lamppit.teste.security;

import br.com.lamppit.teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguracoes {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    protected SecurityFilterChain filterChain  (HttpSecurity httpSecurity) throws Exception {
        
        String gerente = "GERENTE";
        String operador = "OPERADOR";

        httpSecurity.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/gerente").permitAll()
                .antMatchers(HttpMethod.POST,"/operador").permitAll()
               .antMatchers(HttpMethod.POST,"/produto").hasAuthority(gerente)
                .antMatchers(HttpMethod.PUT,"/produto").hasAuthority(gerente)
                .antMatchers(HttpMethod.POST,"/estoque").hasAnyAuthority(gerente,operador)
                .antMatchers(HttpMethod.PUT,"/estoque").hasAnyAuthority(gerente,operador)
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaToken(usuarioRepository,tokenService),
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }



}
