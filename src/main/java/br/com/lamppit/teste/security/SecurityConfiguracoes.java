package br.com.lamppit.teste.security;

import br.com.lamppit.teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfiguracoes {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    protected PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        String entregador = "ENTREGADOR";
        String cliente = "CLIENTE";
        String empresa = "EMPRESA";
        String admin = "ADMIN";

        httpSecurity.authorizeHttpRequests(auth ->
                            auth.requestMatchers(HttpMethod.POST, "/auth").permitAll()
                                .requestMatchers(HttpMethod.GET, "/auth").permitAll()
                                .requestMatchers(HttpMethod.GET, "/error").permitAll()
                                .requestMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                                .requestMatchers(HttpMethod.GET, "/configuration/ui").permitAll()
                                .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/configuration/security").permitAll()
                                .requestMatchers(HttpMethod.GET, ("/swagger-ui/**")).permitAll()
                                .requestMatchers(HttpMethod.GET, ("/swagger/**")).permitAll()
                                .requestMatchers(HttpMethod.GET, ("/webjars/**")).permitAll()
                                .requestMatchers(HttpMethod.POST, "/cliente").hasAnyAuthority(cliente, admin)
                                .requestMatchers(HttpMethod.POST, "/entregador").hasAnyAuthority(entregador, admin)
                                .requestMatchers(HttpMethod.POST, "/empresa/**").hasAnyAuthority(empresa, admin)
                                .requestMatchers(HttpMethod.GET, "/entregador/pedido/entregar").hasAnyAuthority(entregador, admin)
                                .requestMatchers(HttpMethod.PUT, "/entregador/pedido/aceitarDelivery/**").hasAnyAuthority(entregador, admin)
                                .requestMatchers(HttpMethod.POST, "/pedido/*").hasAnyAuthority(cliente, admin)
                                .requestMatchers(HttpMethod.GET, "/pedido/**").hasAnyAuthority(cliente, empresa, admin)
                                .requestMatchers(HttpMethod.GET, "/cliente/menu/**").hasAnyAuthority(cliente, admin)
                                .requestMatchers(HttpMethod.PATCH, "/pedido/statusEmAtendimento/**").hasAnyAuthority(empresa, admin)
                                .requestMatchers(HttpMethod.PATCH, "/pedido/statusConcluido/**").hasAnyAuthority(empresa, admin)
                                .requestMatchers(HttpMethod.PATCH, "/empresa/fechar/**").hasAnyAuthority(empresa, admin)
                                .requestMatchers(HttpMethod.PATCH, "/empresa/abrir/**").hasAnyAuthority(empresa, admin)
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter,UsernamePasswordAuthenticationFilter.class);



        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/favicon.ico"
        );
    }


}
