package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;


    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private CepService cepService;
    @Mock
    public ObjectMapper objectMapper;


    @Mock
    private Cliente cliente;

    @Mock
    private Endereco endereco;


    @Mock
    private ClienteDto clienteDto;

    @Mock
    private EnderecoDto enderecoDto;

    @BeforeEach
    void setUp() {

        enderecoDto = new EnderecoDto("08568000", "2393", "aa");
        clienteDto = new ClienteDto(enderecoDto, "aaa");



    }


    @Test
    void cadastrarCliente() {


        when(modelMapper.map(clienteDto, Cliente.class)).thenReturn(cliente);
        enderecoDto = cepService.viaCep(enderecoDto);
        when(modelMapper.map(enderecoDto, Endereco.class)).thenReturn(endereco);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        when(modelMapper.map(cliente, ClienteDto.class)).thenReturn(clienteDto);

        ClienteDto dto = clienteService.cadastrarCliente(clienteDto);


        assertEquals(dto, clienteDto);
        verify(clienteRepository, times(1)).save(cliente);


    }

}