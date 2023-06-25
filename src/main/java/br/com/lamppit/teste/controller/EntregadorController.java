package br.com.lamppit.teste.controller;


import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.service.impl.EntregadorService;
import br.com.lamppit.teste.service.impl.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {


    @Autowired
    private PedidoService produtoService;
    @Autowired
    private EntregadorService entregadorService;


    @PostMapping
    public ResponseEntity<EntregadorDto> cadastrarEntregador(@RequestBody @Valid EntregadorDto dto,
                                                             UriComponentsBuilder builder) {

        EntregadorDto entregadorDto = entregadorService.cadastrarEntregador(dto);


        URI uri = builder.path("/entregador/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(entregadorDto);


    }

    @PutMapping("/pedido/aceitarDelivery/{id}")
    public ResponseEntity<EntregadorPedidoDto> aceitarDelivery(@RequestBody @Valid PedidoIdDto dto,
                                                                @PathVariable Long id) {

        EntregadorPedidoDto entregadorPedidoDto = entregadorService.aceitarDelivery(dto, id);

        return ResponseEntity.ok(entregadorPedidoDto);

    }

    @GetMapping("/pedido/entregar")
    public Page<PedidoDto> pedidosDisponivelEntrega (Pageable pageable) {

        return produtoService.pedidosDisponiveisEntrega(pageable);
    }


}
