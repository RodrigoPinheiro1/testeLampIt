package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.service.impl.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/empresa/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping("/{id}")
    public ResponseEntity<ProdutoDto> cadastrarProduto (@RequestBody ProdutoDto dto,
                                                        UriComponentsBuilder builder, @PathVariable Long id){

        ProdutoDto produtoDto = produtoService.cadastrarProduto(dto,id);


        URI uri = builder.path("/empresa/{id}").buildAndExpand(dto.getProdutoId()).toUri();
        return ResponseEntity.created(uri).body(produtoDto);


    }
}
