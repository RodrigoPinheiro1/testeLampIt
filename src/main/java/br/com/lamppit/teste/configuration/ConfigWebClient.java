package br.com.lamppit.teste.configuration;


import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ConfigWebClient {
    @Bean
    public WebClient webClient() {

        return WebClient.create("https://viacep.com.br");
    }


}
