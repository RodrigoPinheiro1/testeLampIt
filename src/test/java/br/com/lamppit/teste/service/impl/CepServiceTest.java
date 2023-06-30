package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.exceptions.NotFound.CepNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CepServiceTest {

    @Mock
    private EnderecoDto enderecoDto;

    @Autowired
    private CepService cepService;


    @Mock
    private RestTemplate restTemplate;

    private String uri;


    @BeforeEach
    void setUp() {
        enderecoDto = new EnderecoDto("08568000", "2393", "aa");
        uri = "https://viacep.com.br/ws/" + enderecoDto.getCep() + "/json/";

    }

    @Test
    void viaCepPassingWrongCep() {


        assertNotNull(enderecoDto);
        enderecoDto.setCep("20891230892");

        assertThrows(CepNotFoundException.class, () -> {
            cepService.viaCep(enderecoDto);
        });


    }

    @Test
    void viaCepPassingCorrectCep() {

        assertNotNull(enderecoDto);
        when(restTemplate.getForObject(uri, EnderecoDto.class)).thenReturn(enderecoDto);
        cepService.viaCep(enderecoDto);
        assertDoesNotThrow(CepNotFoundException::new);


    }
}