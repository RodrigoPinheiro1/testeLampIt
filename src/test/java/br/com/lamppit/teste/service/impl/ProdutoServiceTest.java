package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.dto.ProdutoEmpresaDto;
import br.com.lamppit.teste.exceptions.EmpresaFechadaException;
import br.com.lamppit.teste.exceptions.GlobalCustomException;
import br.com.lamppit.teste.exceptions.NotFound.CepNotFoundException;
import br.com.lamppit.teste.exceptions.NotFound.EmpresaNotFoundException;
import br.com.lamppit.teste.model.*;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoServiceTest {

    @MockBean
    private EmpresaRepository empresaRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoDto produtoDto;

    @Mock
    private Empresa empresa;

    @Mock
    private Endereco endereco;


    @MockBean
    private ModelMapper modelMapper;


    @Mock
    private ProdutoEmpresaDto produtoEmpresaDto;

    @InjectMocks
    private NotFoundService notFoundService;
    @Mock
    private Produto produto;
    @Mock
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        endereco = new Endereco("08568000", "2393", "aa");
        empresa = new Empresa("sad", "cpnj", endereco, StatusLoja.ABERTO);

        produtoDto = new ProdutoDto("aa", 1, "aa");

    }

    @Test
    void insertProdutoPassingCorrectInformation() {


        when(modelMapper.map(produtoDto, Produto.class)).thenReturn(produto);

        when(produtoRepository.save(produto)).thenReturn(produto);

        when(empresaRepository.getReferenceById(empresa.getId())).thenReturn(empresa);

        when(modelMapper.map(produto, ProdutoEmpresaDto.class)).thenReturn(this.produtoEmpresaDto);

        produtoService.cadastrarProduto(produtoDto, empresa.getId());


        assertDoesNotThrow(EmpresaNotFoundException::new);


    }

    @Test
    void insertProdutoPassingNullEmpresaId() {


        when(modelMapper.map(produtoDto, Produto.class)).thenReturn(produto);

        when(produtoRepository.save(produto)).thenReturn(produto);

        when(empresaRepository.getReferenceById(isNull())).thenThrow(EmpresaNotFoundException.class);

        produtoService.cadastrarProduto(produtoDto, empresa.getId());

        when(modelMapper.map(produto, ProdutoEmpresaDto.class)).thenReturn(this.produtoEmpresaDto);
    }
}