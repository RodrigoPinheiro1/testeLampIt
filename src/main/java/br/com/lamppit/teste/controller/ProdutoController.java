package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.dto.ProdutoEmpresaDto;
import br.com.lamppit.teste.service.impl.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/empresa/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping("/{id}")
    public ResponseEntity<ProdutoEmpresaDto> cadastrarProduto (@RequestBody  @Valid ProdutoDto dto,
                                                               UriComponentsBuilder builder, @PathVariable Long id){

        ProdutoEmpresaDto produtoDto = produtoService.cadastrarProduto(dto, id);


        URI uri = builder.path("/empresa/produto/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoDto);


    }
}
