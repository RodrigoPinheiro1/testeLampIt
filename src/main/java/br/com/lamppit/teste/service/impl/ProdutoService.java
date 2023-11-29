package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.dto.ProdutoEmpresaDto;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Produto;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {


    private final ModelMapper modelMapper;

    private final FinByIdService finByIdService;

    private final ProdutoRepository produtoRepository;


    public ProdutoEmpresaDto cadastrarProduto(ProdutoDto dto, Long id) {

        Produto produto = modelMapper.map(dto, Produto.class);

        Empresa empresa = finByIdService.seExisteEmpresa(id);

        produto.setEmpresa(empresa);

        produtoRepository.save(produto);

        return modelMapper.map(produto, ProdutoEmpresaDto.class);

    }


}
