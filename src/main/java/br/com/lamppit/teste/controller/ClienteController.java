package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.service.impl.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@Profile({"test","dev","prod"})
@RequiredArgsConstructor
public class ClienteController {


    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteDto dto) {

        ClienteDto clienteDto = clienteService.cadastrarCliente(dto);

        return new ResponseEntity<>(clienteDto, HttpStatus.CREATED);

    }


}
