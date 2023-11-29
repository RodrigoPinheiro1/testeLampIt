package br.com.lamppit.teste.service.impl;


import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.ListProdutoDto;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.StatusLoja;
import br.com.lamppit.teste.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl {


    private final EmpresaRepository empresaRepository;


    private final FinByIdService finByIdService;

    private final ProdutoService produtoService;

    private final ModelMapper modelMapper;

    private final CepService cepService;


    public EmpresaProdutoDto cadastrarEmpresa(EmpresaProdutoDto dto) {

        Empresa empresa = modelMapper.map(dto, Empresa.class);

        EnderecoDto enderecoDto = cepService.viaCep(dto.getEndereco()).block();

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());
        empresa.setEndereco(endereco);

        empresa.setStatusLoja(StatusLoja.ABERTO);
        empresa.getProdutos().forEach(produto -> produto.setEmpresa(empresa));

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaProdutoDto.class);


    }


    public Page<EmpresaProdutoDto> EmpresasDisponiveis(Pageable pageable) {

        return empresaRepository.acharEmpresasQueTenhamProdutos(pageable).map(empresa ->
                modelMapper.map(empresa, EmpresaProdutoDto.class));
    }

    public Page<EmpresaDto> EmpresasDisponiveisNomes(Pageable pageable) {

        return empresaRepository.acharEmpresasQueTenhamProdutos(pageable).map(empresa ->
                modelMapper.map(empresa, EmpresaDto.class));
    }

    public Page<ListProdutoDto> listarProdutos(Pageable pageable, Long id) {

        finByIdService.seProdutoExiste(id);

        return empresaRepository.findAllById(id, pageable).
                map(empresa -> modelMapper.map(empresa, ListProdutoDto.class));

    }

    public EmpresaDto fecharLoja(Long id) {

        Empresa empresa = finByIdService.seExisteEmpresa(id);

        empresa.setId(id);
        empresa.setNome(empresa.getNome());
        empresa.setCnpj(empresa.getCnpj());
        empresa.setStatusLoja(StatusLoja.FECHADO);

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaDto.class);
    }

    public EmpresaDto abrirLoja(Long id) {

        Empresa empresa = finByIdService.seExisteEmpresa(id);

        empresa.setId(id);
        empresa.setNome(empresa.getNome());
        empresa.setCnpj(empresa.getCnpj());
        empresa.setStatusLoja(StatusLoja.ABERTO);

        empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaDto.class);

    }
}
