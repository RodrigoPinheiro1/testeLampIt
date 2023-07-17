package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.exceptions.NotFound.CepNotFoundException;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.service.impl.CepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    private final URI uri = URI.create("/cliente");
    @Mock
    private ClienteDto clienteDto;
    @MockBean
    private Cliente cliente;

    @MockBean
    private Endereco endereco;

    @Autowired
    private CepService cepService;

    @Autowired
    private ClienteRepository clienteRepository;
    @MockBean
    private EnderecoDto enderecoDto;


    @BeforeEach
    void setUp(){
        endereco = new Endereco("08568000", "2393", "aa");
        cliente = new Cliente(endereco, "aaa");
        clienteRepository.save(cliente);

        enderecoDto = new EnderecoDto("08568000", "2393", "aa");
        clienteDto = new ClienteDto(enderecoDto, "aaa");



    }

    @Test
    void shouldInsertClientPassingCorrectsInformations() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }
    @Test
    void shouldNotInsertClientPassingWrongInformations() throws Exception {

        clienteDto.setEndereco(null);

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    void shouldReturnCepNotFoundPassingCepWrong() throws Exception {

        enderecoDto.setCep("8u213-9i213-3r");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        assertThrows(CepNotFoundException.class,() -> cepService.viaCep(enderecoDto));

    }






}