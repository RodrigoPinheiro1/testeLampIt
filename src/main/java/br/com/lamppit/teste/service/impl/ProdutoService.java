package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.dto.ProdutoEmpresaDto;
import br.com.lamppit.teste.exceptions.ProductNotFound;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Produto;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoEmpresaDto cadastrarProduto(ProdutoDto dto, Long id) {

        Produto produto = modelMapper.map(dto, Produto.class);


        Empresa empresa = empresaRepository.getReferenceById(id);

        produto.setEmpresa(empresa);

        produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoEmpresaDto.class);

    }


}
