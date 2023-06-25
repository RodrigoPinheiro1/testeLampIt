package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.model.Produto;
import br.com.lamppit.teste.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {


    @Autowired
    private EmpresaServiceImpl empresaService;


    @PostMapping
    public ResponseEntity<EmpresaProdutoDto> cadastrarEmpresa(@RequestBody @Valid EmpresaProdutoDto dto, UriComponentsBuilder builder){

        EmpresaProdutoDto empresaDto = empresaService.cadastrarEmpresa(dto);

        URI uri = builder.path("/empresa/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(empresaDto);

    }


    @GetMapping
    public Page<EmpresaProdutoDto> page (Pageable pageable) {

        return empresaService.paginacao(pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaDto> fecharLoja (@PathVariable Long id){

        EmpresaDto empresaDto = empresaService.fecharLoja(id);

        return ResponseEntity.ok(empresaDto);

    }

}
