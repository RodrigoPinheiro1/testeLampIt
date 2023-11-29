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
@Profile({"test","dev","prod"})
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

    @GetMapping("/andamento")
    public Page<PedidoDto> pedidosStatusEmAndamento(Pageable pageable) {
        return pedidoService.pedidosStatusEmAndamento(pageable);
    }

    @GetMapping("/naoEntregue")
    public Page<PedidoDto> pedidosNaoEntregues (Pageable pageable) {

        return pedidoService.pedidosNaoEntregues(pageable);
    }

    @GetMapping
    public Page<PedidoDto> pedidos (Pageable pageable) {

        return pedidoService.pedidos(pageable);
    }

    @PatchMapping("/statusEmAtendimento/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaEmAtendimento(@PathVariable Long id) {

        PedidoDto pedidoDto = pedidoService.atualizarPedidoParaEmAtendimento(id);

        return ResponseEntity.ok(pedidoDto);
    }
    @PatchMapping("/statusConcluido/{id}")
    public ResponseEntity<PedidoDto> atualizaPedidoParaConcluido(@PathVariable Long id) {

        PedidoDto pedidoDto = pedidoService.atualizaPedidoParaConcluido(id);

        return ResponseEntity.ok(pedidoDto);

    }





}
