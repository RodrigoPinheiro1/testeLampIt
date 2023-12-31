package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.dto.TokenDto;
import br.com.lamppit.teste.exceptions.AutenticacaoException;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.service.impl.AuthenticationService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth controller")
@Profile("prod")
public class AuthenticationUserController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {



        String token = authenticationService.autenticar(form);


        return ResponseEntity.ok(new TokenDto(token, "Bearer"));

    }

    @GetMapping
    public List<Perfil> perfils() {
        return authenticationService.acharTodos();

    }


}
