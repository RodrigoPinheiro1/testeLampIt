package br.com.lamppit.teste.controller;

import br.com.lamppit.teste.security.SecurityConfiguracoes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = SecurityConfiguracoes.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class ClienteControllerTest {


    @BeforeEach
    void setUp() {
    }

    @WithMockUser(username = "ADMIN")
    @Test
    void cadastrarCliente() {


    }

    @Test
    void pedidosNaoEntregues() {
    }
}