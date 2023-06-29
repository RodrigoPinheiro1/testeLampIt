package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.service.impl.EmpresaServiceImpl;
import br.com.lamppit.teste.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {


    @Autowired
    private EmpresaServiceImpl empresaService;

    @Autowired
    private PedidoService produtoService;


    @PostMapping
    public ResponseEntity<EmpresaProdutoDto> cadastrarEmpresa(@RequestBody @Valid EmpresaProdutoDto dto, UriComponentsBuilder builder){

        EmpresaProdutoDto empresaDto = empresaService.cadastrarEmpresa(dto);

        URI uri = builder.path("/empresa/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(empresaDto);

    }

        @GetMapping("/pedido/andamento")
    public Page<PedidoDto> pedidosStatusEmAndamento(Pageable pageable) {
        return produtoService.pedidosStatusEmAndamento(pageable);
    }


    @GetMapping
    public Page<EmpresaProdutoDto> EmpresasDisponiveis (Pageable pageable) {

        return empresaService.EmpresasDisponiveis(pageable);
    }

    @PatchMapping("/fechar/{id}")
    public ResponseEntity<EmpresaDto> fecharLoja (@PathVariable Long id){

        EmpresaDto empresaDto = empresaService.fecharLoja(id);

        return ResponseEntity.ok(empresaDto);

    }

    @PatchMapping("/abrir/{id}")
    public ResponseEntity<EmpresaDto> abrirLoja (@PathVariable Long id){

        EmpresaDto empresaDto = empresaService.abrirLoja(id);

        return ResponseEntity.ok(empresaDto);

    }

}
