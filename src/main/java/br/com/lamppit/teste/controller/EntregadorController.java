package br.com.lamppit.teste.controller;


import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.service.impl.EntregadorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    //fazer cadastro entregador,

    //fazer vinculo com o pedido e entregador,
//    avisar a loja  quando ele for buscar.

    @Autowired
    private EntregadorService entregadorService;


    @PostMapping
    public ResponseEntity<EntregadorDto> cadastrarEntregador(@RequestBody @Valid EntregadorDto dto,
                                                             UriComponentsBuilder builder) {

        EntregadorDto entregadorDto = entregadorService.cadastrarEntregador(dto);


        URI uri = builder.path("/entregador/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(entregadorDto);


    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<EntregadorPedidoDto> cadastrarProduto(@RequestBody @Valid PedidoIdDto dto,
                                                                @PathVariable Long id) {

        EntregadorPedidoDto entregadorPedidoDto = entregadorService.aceitarDelivery(dto, id);

        return ResponseEntity.ok(entregadorPedidoDto);

    }


}
