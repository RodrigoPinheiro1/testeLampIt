package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.dto.ProdutoDtoId;
import br.com.lamppit.teste.model.*;
import br.com.lamppit.teste.model.situacao.Cadastrado;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private URI uri = URI.create("/pedido");
    private URI statusEmAtendimento = URI.create("/pedido/statusEmAtendimento");
    private URI statusConcluido = URI.create("/pedido/statusConcluido");

    @MockBean
    private Endereco endereco;

    @MockBean
    private Empresa empresa;

    @MockBean
    private Produto produto;

    @Autowired
    private ProdutoRepository produtoRepository;

    @MockBean
    private Cliente cliente;

    @Autowired
    private ClienteRepository clienteRepository;

    @MockBean
    private ProdutoDtoId produtoDtoId;
    @MockBean
    private PedidoEmpresaIdDto pedidoEmpresaIdDto;
    @Autowired
    private EmpresaRepository empresaRepository;

    @MockBean
    private Pedido pedido;

    @Autowired
    private PedidoRepository pedidoRepository;


    @Mock
    private Entregador entregador;

    @BeforeEach
    void setUp() {
        produto = new Produto("ads", 1, "ads");
        endereco = new Endereco("08568000", "2393", "aa");
        empresa = new Empresa("sad", "59.291.534/0001-67", endereco, StatusLoja.ABERTO);
        cliente = new Cliente(endereco, "aaa");
        clienteRepository.save(cliente);
        empresaRepository.save(empresa);


        pedido = new Pedido(LocalDateTime.now(), Status.CADASTRADO,
                FormaPagamento.DINHEIRO, FormaEntrega.DELIVERY,
                new Cadastrado(), cliente, empresa, Collections.singletonList(produto));


        pedidoRepository.save(pedido);
        produtoRepository.save(produto);

        produtoDtoId = new ProdutoDtoId(produto.getId());

        pedidoEmpresaIdDto = new PedidoEmpresaIdDto(FormaPagamento.DINHEIRO, FormaEntrega.DELIVERY, empresa.getId(),
                Collections.singletonList(produtoDtoId));

    }

    @Test
    void cadastrarPedido() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedidoEmpresaIdDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void pedidosStatusEmAndamento() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/andamento"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void pedidosNaoEntregues() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/naoEntregue"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void pedidos() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void atualizaPedidoParaEmAtendimento() throws Exception {

        pedido.setStatus(Status.CADASTRADO);
        pedidoRepository.save(pedido);
        mockMvc.perform(MockMvcRequestBuilders.patch(statusEmAtendimento
                        + "/" + pedido.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void atualizaPedidoParaConcluido() throws Exception {

        pedido.setStatus(Status.EM_ATENDIMENTO);
        pedidoRepository.save(pedido);


        mockMvc.perform(MockMvcRequestBuilders.patch(statusConcluido
                        + "/" + pedido.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void shouldNotUpdateWrongStatus() throws Exception {

        pedido.setStatus(Status.ENTREGA_CONFIRMADA);
        pedidoRepository.save(pedido);


        mockMvc.perform(MockMvcRequestBuilders.patch(statusConcluido
                        + "/" + pedido.getId()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void shouldReturn404IdIsNotExits() throws Exception {

        pedido.setId(0L);
        mockMvc.perform(MockMvcRequestBuilders.patch(statusConcluido
                        + "/" + pedido.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }



}