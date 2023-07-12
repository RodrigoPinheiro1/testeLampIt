package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.ProdutoDto;
import br.com.lamppit.teste.exceptions.GlobalCustomException;
import br.com.lamppit.teste.exceptions.NotFound.CepNotFoundException;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.Produto;
import br.com.lamppit.teste.model.StatusLoja;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.service.impl.CepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmpresaControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final URI uri = URI.create("/empresa");

    @MockBean
    private Endereco endereco;

    @MockBean
    private Empresa empresa;

    @MockBean
    private Produto produto;

    @MockBean
    private ProdutoDto produtoDto;

    @MockBean
    private EmpresaProdutoDto empresaProdutoDto;
    @Autowired
    private CepService cepService;

    @Autowired
    private EmpresaRepository empresaRepository;
    @MockBean
    private EnderecoDto enderecoDto;

    @BeforeEach
    void setUp() {
        produto = new Produto("ads", 1, "ads", empresa);
        empresa = new Empresa("sad", "59.291.534/0001-67", endereco, StatusLoja.ABERTO);
        endereco = new Endereco("08568000", "2393", "aa");
        empresaRepository.save(empresa);

        produtoDto = new ProdutoDto("nome", 1, "descricao");
        enderecoDto = new EnderecoDto("08568000", "2393", "aa");
        empresaProdutoDto = new EmpresaProdutoDto(1L, "nome", "59.291.534/0001-67", "email",
                enderecoDto, StatusLoja.ABERTO, Collections.singletonList(produtoDto));


    }

    @Test
    void shouldInsertBusiness() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaProdutoDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        assertDoesNotThrow(CepNotFoundException::new);
        assertDoesNotThrow(GlobalCustomException::new);


    }

    @Test
    void shouldReturnBadRequestPassingNullsInformations() throws Exception {


        empresaProdutoDto.setCnpj(null);
        empresaProdutoDto.setEndereco(null);

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaProdutoDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    void shouldReturnCepNotFoundPassingCepWrong() throws Exception {

        enderecoDto.setCep("8u213-9i213-3r");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empresaProdutoDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        assertThrows(CepNotFoundException.class, () -> cepService.viaCep(enderecoDto));

    }

    @Test
    void shouldAllBusinessAvailable() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldCloseStore() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch(uri+"/fechar/"+empresa.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void shouldReturnNotFoundStore() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch(uri+"/fechar/00000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldOpenStore() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch(uri+"/abrir/"+empresa.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void shouldReturnNotFoundOpenStore() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch(uri+"/abrir/000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}