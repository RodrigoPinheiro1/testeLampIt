package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.exceptions.EmpresaFechadaException;
import br.com.lamppit.teste.exceptions.NotFound.ClienteNotFoundException;
import br.com.lamppit.teste.exceptions.NotFound.EmpresaNotFoundException;
import br.com.lamppit.teste.model.*;
import br.com.lamppit.teste.model.situacao.Cadastrado;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidoServiceTest {

    @MockBean
    private EmpresaRepository empresaRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoDto produtoDto;

    @Mock
    private PedidoEmpresaIdDto pedidoEmpresaIdDto;
    @Mock
    private PedidoDto pedidoDto;

    @Mock
    private Empresa empresa;

    @Mock
    private Endereco endereco;

    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private ModelMapper modelMapper;

    @Mock
    private Cliente cliente;

    @InjectMocks
    private Pedido pedido;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoEmpresaDto produtoEmpresaDto;

    @MockBean
    private ProdutoDtoId produtoDtoId;
    @Mock
    private NotFoundService notFoundService;

    @Mock
    private Pageable pageable;
    @MockBean
    private PedidoService pedidoService;
    @Mock
    private Produto produto;

    @Mock
    private Entregador entregador;
    @Mock
    Page<Pedido> pedidos;

    @BeforeEach
    void setUp() {

        endereco = new Endereco("08568000", "2393", "aa");
        cliente = new Cliente(1L, endereco, "aaa");
        entregador = new Entregador(1L, "aaa", Collections.singletonList(pedido));
        empresa = new Empresa(1L, "sad", "59.291.534/0001-67", endereco, StatusLoja.ABERTO);
        produto = new Produto(1L, "ads", 1, "ads", empresa);
        produtoDtoId = new ProdutoDtoId(1L);
        pedido = new Pedido(1L, LocalDateTime.now(), Status.CADASTRADO,
                FormaPagamento.DINHEIRO, FormaEntrega.DELIVERY, new Cadastrado(), entregador, cliente, empresa, Collections.singletonList(produto));

        pedidoEmpresaIdDto = new PedidoEmpresaIdDto(FormaPagamento.DINHEIRO, FormaEntrega.DELIVERY,
                1L, Collections.singletonList(produtoDtoId));

    }


    @Test
    void cadastrarPedido() {


        when(modelMapper.map(pedidoEmpresaIdDto, Pedido.class)).thenReturn(pedido);


        when(pedidoRepository.save(pedido)).thenReturn(pedido);
        when(clienteRepository.getReferenceById(anyLong())).thenReturn(cliente);
        when(empresaRepository.getReferenceById(anyLong())).thenReturn(empresa);
        when(empresaRepository.seEmpresaEstaAberto(anyLong())).thenReturn(Optional.ofNullable(empresa));

        pedidoService.cadastrarPedido(pedidoEmpresaIdDto, cliente.getId());

        assertDoesNotThrow(EmpresaNotFoundException::new);
        assertDoesNotThrow(ClienteNotFoundException::new);
        assertDoesNotThrow(EmpresaFechadaException::new);

        assertNotNull(pedido);
        assertNotNull(pedidoDto);
        assertNotNull(cliente);
        assertNotNull(empresa);
        assertNotNull(produtoDtoId);

        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);
    }

    @Test
    void pedidosStatusEmAndamento() {


        when(pedidoRepository.pedidosStatusEmAndamento(pageable)).thenReturn(pedidos);
        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        assertNotNull(pedidoDto);
        assertNotNull(pedido);
        pedidoService.pedidosStatusEmAndamento(pageable);

    }

    @Test
    void pedidosDisponiveisEntrega() {


        when(pedidoRepository.pedidosDisponiveisEntrega(pageable)).thenReturn(pedidos);
        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        assertNotNull(pedidoDto);
        assertNotNull(pedido);
        pedidoService.pedidosDisponiveisEntrega(pageable);


    }

    @Test
    void atualizaPedidoParaConcluido() {


        pedido.setStatus(Status.EM_ATENDIMENTO);

        assertNotNull(pedido);
        when(pedidoService.getPedidoDto(pedido)).thenReturn(pedidoDto);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        assertEquals(Status.EM_ATENDIMENTO, pedido.getStatus());
        pedidoService.atualizaPedidoParaConcluido(pedido.getId());




    }

    @Test
    void atualizarPedidoParaEmAtendimento() {


        assertNotNull(pedido);
        when(pedidoService.getPedidoDto(pedido)).thenReturn(pedidoDto);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        assertEquals(Status.CADASTRADO, pedido.getStatus());
        pedidoService.atualizarPedidoParaEmAtendimento(pedido.getId());
    }



    @Test
    void pedidosNaoEntregues() {

        when(pedidoRepository.pedidosStatusNaoEntregue(pageable)).thenReturn(pedidos);
        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        assertNotNull(pedidoDto);
        assertNotNull(pedido);
        pedidoService.pedidosNaoEntregues(pageable);

    }

    @Test
    void pedidos() {


        when(pedidoRepository.findAll(pageable)).thenReturn(pedidos);
        assertNotNull(pedidoDto);
        assertNotNull(pedido);
        when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);
        pedidoService.pedidos(pageable);
    }
}