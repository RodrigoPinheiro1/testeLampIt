package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.Usuario;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.repository.UsuarioRepository;
import br.com.lamppit.teste.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import springfox.documentation.spring.web.json.Json;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final URI uri = URI.create("/cliente");
    @Mock
    private ClienteDto clienteDto;

    @MockBean
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private LoginForm form;
    @MockBean
    private Cliente cliente;

    @MockBean
    private Endereco endereco;

    private String token;
    @Autowired
    private ClienteRepository clienteRepository;
    @MockBean
    private EnderecoDto enderecoDto;
    @MockBean
    private JSONObject jsonObject;

    @BeforeEach
    void setUp(){
        endereco = new Endereco("08568000", "2393", "aa");
        cliente = new Cliente(endereco, "aaa");
        clienteRepository.save(cliente);



        enderecoDto = new EnderecoDto("08568000", "2393", "aa");
        clienteDto = new ClienteDto(enderecoDto, "aaa");

    }

    @Test
    void cadastrarCliente() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri).
                        contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }

    @Test
    void pedidosNaoEntregues() {
    }
}