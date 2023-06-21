package br.com.lamppit.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.lamppit.teste.dto.LoginRequestDto;
import br.com.lamppit.teste.dto.UserLoginDto;
import br.com.lamppit.teste.service.AuthenticateUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth controller")
public class AuthenticationUserController {

    @Autowired
    private AuthenticateUserService service;

    @PostMapping("/login")
    @ApiOperation(value = "Endpoint de login para utilização do restante dos endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginDto> login(@Valid @RequestBody LoginRequestDto dto) throws Exception {

        UserLoginDto userDto = service.login(dto.getUsername(), dto.getPassword());

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", userDto.getToken());
        return ResponseEntity.ok().headers(header).body(userDto);
    }

}
