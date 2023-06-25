package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService produtoService;


    @PostMapping("/{id}")
    public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoEmpresaIdDto dto, @PathVariable Long id,
                                                     UriComponentsBuilder builder) {

        PedidoDto pedidoDto = produtoService.cadastrarPedido(dto,id);

        URI uri = builder.path("/pedido/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(pedidoDto);

    }

    @GetMapping
    public Page<PedidoDto> pedidos (Pageable pageable) {

        return produtoService.pedidos(pageable);
    }

    @PatchMapping("/empresa/atualizarStatus/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaEmAtendimento(@PathVariable Long id) {

        PedidoDto pedidoDto = produtoService.atualizarPedidoParaEmAtendimento(id);

        return ResponseEntity.ok(pedidoDto);
    }

}
