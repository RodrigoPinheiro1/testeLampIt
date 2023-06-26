package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.dto.TokenDto;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.repository.PerfilRepository;
import br.com.lamppit.teste.security.TokenService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth controller")
public class AuthenticationUserController {



    @Autowired
    private TokenService tokenService;


    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Perfil> perfils () {
        return perfilRepository.findAll();
    }


}
