package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.exceptions.NotFound.CepNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CepService {


    private final WebClient webClient;

    public Mono<EnderecoDto> viaCep (EnderecoDto enderecoDto){

        try {

         return webClient.get().uri(uriBuilder ->
                    uriBuilder.path("/ws/{cep}/json/")
                            .build(enderecoDto.getCep()))
                    .retrieve()
                    .bodyToMono(EnderecoDto.class);

//            RestTemplate template = new RestTemplate();
//
//            String uri = "https://viacep.com.br/ws/" + enderecoDto.getCep() + "/json/";
//            return template.getForObject(uri, EnderecoDto.class);

        }catch (WebClientRequestException e){
            throw new CepNotFoundException();
        }catch (WebClientResponseException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
