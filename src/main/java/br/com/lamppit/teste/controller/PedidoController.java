package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PedidoService produtoService;


    @PostMapping
    public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody PedidoDto dto,
                                                     UriComponentsBuilder builder) {

        PedidoDto pedidoDto = produtoService.cadastrarPedido(dto);

        URI uri = builder.path("/pedido/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoDto);

    }

    @GetMapping("/andamento")
    public Page<PedidoDto> pedidosStatusEmAndamento(Pageable pageable) {
        return produtoService.pedidosStatusEmAndamento(pageable);
    }

    @GetMapping("/naoEntregue")
    public Page<PedidoDto> pedidosNaoEntregues (Pageable pageable) {

        return produtoService.pedidosNaoEntregues(pageable);
    }

    @GetMapping("/entregar")
    public Page<PedidoDto> pedidosDisponivelEntrega (Pageable pageable) {

        return produtoService.pedidosDisponiveisEntrega(pageable);
    }



    @GetMapping
    public Page<PedidoDto> pedidos (Pageable pageable) {

        return produtoService.pedidos(pageable);
    }


    @PatchMapping("/atendimento/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaEmAtendimento(@PathVariable Long id) {

        PedidoDto pedidoDto = produtoService.atualizarPedidoParaEmAtendimento(id);

        return ResponseEntity.ok(pedidoDto);
    }

    @PatchMapping("/entregue/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaEntregue(@PathVariable Long id) {

        PedidoDto pedidoDto = produtoService.atualizarPedidoParaEntregue(id);

        return ResponseEntity.ok(pedidoDto);
    }

    @PatchMapping("/concluido/{id}")
    public ResponseEntity<PedidoDto> atualizarStatusPedidoConcluido( @PathVariable Long id) {

        PedidoDto pedidoDto = produtoService.atualizarPedidoConcluido(id);

        return ResponseEntity.ok(pedidoDto);
    }


}
