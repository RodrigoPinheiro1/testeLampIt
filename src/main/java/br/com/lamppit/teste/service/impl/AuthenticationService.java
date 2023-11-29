package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.model.Usuario;
import br.com.lamppit.teste.repository.PerfilRepository;
import br.com.lamppit.teste.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final TokenService tokenService;

    private final PerfilRepository perfilRepository;
    private final AuthenticationManager authenticationManager;

    public String autenticar(LoginForm form) {


        var authenticationToken = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        return tokenService.gerarToken((Usuario) authentication.getPrincipal());

    }

    public List<Perfil> acharTodos() {
        return perfilRepository.findAll();
    }

}

