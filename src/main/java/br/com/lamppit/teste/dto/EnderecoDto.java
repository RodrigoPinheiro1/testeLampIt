package br.com.lamppit.teste.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EnderecoDto {

    @NotNull
    @NotEmpty
    private String cep;
    @NotNull
    @NotEmpty
    private String numero;

    private String logradouro;

    @NotNull
    @NotEmpty
    private String complemento;

    private String bairro;
    private String localidade;


    public EnderecoDto(String cep, String numero, String complemento) {
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }
}
