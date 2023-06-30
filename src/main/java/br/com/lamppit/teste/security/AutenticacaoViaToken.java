package br.com.lamppit.teste.security;


import br.com.lamppit.teste.model.Usuario;
import br.com.lamppit.teste.repository.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaToken extends OncePerRequestFilter {

    private UsuarioRepository usuarioRepository;
    private TokenService tokenService; //não é possivel adicionar  dependecias com autowired,  aqui.


    public AutenticacaoViaToken(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }
    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7); //pega apartir do espaço
    }

    private void autenticar (String token){
        Long id  = tokenService.getId(token);
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario,
                null,usuario.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean tokenValido = tokenService.isTokenValido(token);
        if (tokenValido){
            autenticar(token);
        }
        filterChain.doFilter(request,response);
    }
}
