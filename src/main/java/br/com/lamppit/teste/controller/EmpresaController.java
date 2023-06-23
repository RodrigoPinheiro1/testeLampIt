package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.ListProdutoDto;
import br.com.lamppit.teste.model.Produto;
import br.com.lamppit.teste.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {


    @Autowired
    private EmpresaServiceImpl empresaService;


    @PostMapping
    public ResponseEntity<EmpresaProdutoDto> cadastrarEmpresa(@RequestBody EmpresaProdutoDto dto, UriComponentsBuilder builder){

        EmpresaProdutoDto empresaDto = empresaService.cadastrarEmpresa(dto);

        URI uri = builder.path("/empresa/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(empresaDto);

    }



    @GetMapping
    public Page<EmpresaProdutoDto> page (Pageable pageable) {

        return empresaService.paginacao(pageable);
    }


}
