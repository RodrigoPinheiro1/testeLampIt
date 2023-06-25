package br.com.lamppit.teste.service.impl;


import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.ListProdutoDto;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.StatusLoja;
import br.com.lamppit.teste.repository.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private NotFoundService notFoundService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ModelMapper modelMapper;


    public EmpresaProdutoDto cadastrarEmpresa(EmpresaProdutoDto dto) {

        Empresa empresa = modelMapper.map(dto, Empresa.class);

        empresa.setStatusLoja(StatusLoja.ABERTO);
        empresa.getProdutos().forEach(produto -> produto.setEmpresa(empresa));


        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaProdutoDto.class);


    }


    public Page<EmpresaProdutoDto> paginacaoEmpresas(Pageable pageable) {

        return empresaRepository.acharEmpresasQueTenhamProdutos(pageable).map(empresa ->
                modelMapper.map(empresa, EmpresaProdutoDto.class));
    }

    public Page<EmpresaDto> paginacaoEmpresa(Pageable pageable) {
        return empresaRepository.findAll(pageable).
                map(empresa -> modelMapper.map(empresa, EmpresaDto.class));
    }

    public Page<ListProdutoDto> listarProdutos(Pageable pageable, Long id) {

        notFoundService.seProdutoExiste(id);

        return empresaRepository.findAllById(id, pageable).
                map(empresa -> modelMapper.map(empresa, ListProdutoDto.class));

    }


    public EmpresaDto fecharLoja(Long id) {

        notFoundService.seExisteEmpresa(id);

        Empresa empresa = empresaRepository.getReferenceById(id);

        empresa.setId(id);
        empresa.setNome(empresa.getNome());
        empresa.setCnpj(empresa.getCnpj());
        empresa.setStatusLoja(StatusLoja.FECHADO);

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaDto.class);

    }

    public EmpresaDto abrirLoja(Long id) {

        notFoundService.seExisteEmpresa(id);

        Empresa empresa = empresaRepository.getReferenceById(id);
        empresa.setId(id);
        empresa.setNome(empresa.getNome());
        empresa.setCnpj(empresa.getCnpj());
        empresa.setStatusLoja(StatusLoja.ABERTO);

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaDto.class);

    }
}
