package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.service.impl.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/{id}")
    public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoEmpresaIdDto dto, @PathVariable Long id,
                                                     UriComponentsBuilder builder) {

        PedidoDto pedidoDto = pedidoService.cadastrarPedido(dto,id);

        URI uri = builder.path("/pedido/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoDto);

    }

    @GetMapping
    public Page<PedidoDto> pedidos (Pageable pageable) {

        return pedidoService.pedidos(pageable);
    }

    @PatchMapping("/empresa/statusEmAtendimento/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaEmAtendimento(@PathVariable Long id) {

        PedidoDto pedidoDto = pedidoService.atualizarPedidoParaEmAtendimento(id);

        return ResponseEntity.ok(pedidoDto);
    }
    @PatchMapping("/empresa/statusConcluido/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaConcluido(@PathVariable Long id) {

        PedidoDto pedidoDto = pedidoService.atualizaPedidoParaConcluido(id);

        return ResponseEntity.ok(pedidoDto);

    }



//    @PatchMapping("/empresa/StatusEntregaConfirmada/{id}")
//    public ResponseEntity<PedidoDto> atualizaPedidoParaEntregaConfirmada(@PathVariable Long id) {
//
//        PedidoDto pedidoDto = pedidoService.atualizaPedidoParaEntregaConfirmada(id);
//
//        return ResponseEntity.ok(pedidoDto);
//    }
//    @PatchMapping("/empresa/StatusEntregue/{id}")
//    public ResponseEntity<PedidoDto> atualizaPedidoParaEntregue(@PathVariable Long id) {
//
//        PedidoDto pedidoDto = pedidoService.atualizaPedidoParaEntregue(id);
//
//        return ResponseEntity.ok(pedidoDto);
//    }

}
