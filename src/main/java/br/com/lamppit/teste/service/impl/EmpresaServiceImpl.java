package br.com.lamppit.teste.service.impl;


import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.ListProdutoDto;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import br.com.lamppit.teste.service.EmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmpresaProdutoDto cadastrarEmpresa(EmpresaProdutoDto dto) {

        Empresa empresa = modelMapper.map(dto, Empresa.class);

        empresa.getProdutos().forEach(produto -> produto.setEmpresa(empresa));


        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaProdutoDto.class);


    }

 //   @Override
    public EmpresaProdutoDto cadastrarProdutoEmpresa(ListProdutoDto dto, Long id) {

        Empresa empresa = modelMapper.map(dto, Empresa.class);


        empresa.setId(id);

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaProdutoDto.class);

    }


    public Page<EmpresaProdutoDto> paginacao(Pageable pageable) {

        return empresaRepository.findAll(pageable).map(empresa ->
            modelMapper.map(empresa, EmpresaProdutoDto.class));
    }

    public Page<EmpresaDto> paginacaoEmpresa(Pageable pageable) {
       return empresaRepository.findAll(pageable).
               map(empresa -> modelMapper.map(empresa,EmpresaDto.class));
    }

    public Page<ListProdutoDto> listarProdutos(Pageable pageable,Long id) {

      return   empresaRepository.findAllById(id,pageable).
              map(empresa -> modelMapper.map(empresa, ListProdutoDto.class));

    }
}
