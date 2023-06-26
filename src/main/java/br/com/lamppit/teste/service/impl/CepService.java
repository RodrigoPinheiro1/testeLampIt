package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.EnderecoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {


    public EnderecoDto viaCep (EnderecoDto enderecoDto){

        RestTemplate template = new RestTemplate();

        String uri = "https://viacep.com.br/ws/" + enderecoDto.getCep()+"/json/";

        return template.getForObject(uri, EnderecoDto.class);
    }
}