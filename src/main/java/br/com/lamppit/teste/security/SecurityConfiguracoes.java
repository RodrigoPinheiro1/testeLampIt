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
        
        String entregador = "ENTREGADOR";
        String cliente = "CLIENTE";
        String empresa = "EMPRESA";
        String admin = "ADMIN";

        httpSecurity.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET,"/configuration/ui").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET,"/configuration/security").permitAll()
                .antMatchers(HttpMethod.GET,("/swagger-ui/**")).permitAll()
                .antMatchers(HttpMethod.GET,("/swagger/**")).permitAll()
                .antMatchers(HttpMethod.GET,("/webjars/**")).permitAll()
                .antMatchers(HttpMethod.POST,"/cliente").hasAnyAuthority(cliente,admin)
                .antMatchers(HttpMethod.POST,"/entregador").hasAnyAuthority(entregador,admin)
                .antMatchers(HttpMethod.POST,"/empresa/**").hasAnyAuthority(empresa,admin)
                .antMatchers(HttpMethod.GET,"/entregador/pedido/entregar").hasAnyAuthority(entregador,admin)
                .antMatchers(HttpMethod.PUT,"/entregador/pedido/aceitarDelivery/**").hasAnyAuthority(entregador,admin)
                .antMatchers(HttpMethod.POST,"/pedido/*").hasAnyAuthority(cliente,admin)
                .antMatchers(HttpMethod.GET,"/pedido/**").hasAnyAuthority(cliente,empresa,admin)
                .antMatchers(HttpMethod.GET,"/cliente/menu/**").hasAnyAuthority(cliente,admin)
                .antMatchers(HttpMethod.PATCH,"/empresa/atualizarStatus/**").hasAnyAuthority(empresa,admin)
                .antMatchers(HttpMethod.PATCH,"/empresa/fechar/**").hasAnyAuthority(empresa,admin)
                .antMatchers(HttpMethod.PATCH,"/empresa/abrir/**").hasAnyAuthority(empresa,admin)
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaToken(usuarioRepository,tokenService),
                UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }



}
