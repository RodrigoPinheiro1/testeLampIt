package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.ListProdutoDto;
import br.com.lamppit.teste.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente/menu")
public class MenuController {


    @Autowired
    private EmpresaServiceImpl empresaService;


    @GetMapping
    public Page<EmpresaDto> paginacaoEmpresa(Pageable pageable) {

     return  empresaService.EmpresasDisponiveisNomes(pageable);
    }

    @GetMapping("/{id}")
    public Page<ListProdutoDto> listaDeProdutos (@PathVariable Long id, Pageable pageable) {


        return empresaService.listarProdutos(pageable,id);
    }


}
