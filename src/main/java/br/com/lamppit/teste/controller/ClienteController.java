package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.service.impl.ClienteService;
import br.com.lamppit.teste.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cliente")
@Profile({"test","dev","prod"})
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService produtoService;


    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteDto dto,
                                                      UriComponentsBuilder builder) {

        ClienteDto clienteDto = clienteService.cadastrarCliente(dto);

        URI uri = builder.path("/cliente/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteDto);

    }

    @GetMapping("/pedido/naoEntregue")
    public Page<PedidoDto> pedidosNaoEntregues (Pageable pageable) {

        return produtoService.pedidosNaoEntregues(pageable);
    }


}
