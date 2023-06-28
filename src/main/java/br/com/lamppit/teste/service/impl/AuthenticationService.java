package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.dto.TokenDto;
import br.com.lamppit.teste.exceptions.AutenticacaoException;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.repository.PerfilRepository;
import br.com.lamppit.teste.repository.UsuarioRepository;
import br.com.lamppit.teste.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {


    @Autowired
    private TokenService tokenService;


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenDto autenticar(LoginForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {

            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return new TokenDto(token, "Bearer");
        }catch (AuthenticationException e){

            throw new RuntimeException(e.getMessage());
        }

    }

    public List<Perfil> acharTodos() {
        return perfilRepository.findAll();
    }
}

