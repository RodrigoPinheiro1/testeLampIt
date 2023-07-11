package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.dto.TokenDto;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.model.Usuario;
import br.com.lamppit.teste.repository.PerfilRepository;
import br.com.lamppit.teste.repository.UsuarioRepository;
import br.com.lamppit.teste.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService  {


    @Autowired
    private TokenService tokenService;


    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String autenticar(LoginForm form) {



        var authenticationToken = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        return tokenService.gerarToken((Usuario) authentication.getPrincipal());

    }

    public List<Perfil> acharTodos() {
        return perfilRepository.findAll();
    }

}

