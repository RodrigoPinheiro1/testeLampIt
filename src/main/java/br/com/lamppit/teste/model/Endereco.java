package br.com.lamppit.teste.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    @NotNull
    private String cep;
    private String numero;
    private String logradouro;
    private String complemento;

    private String bairro;
    private String localidade;

    public Endereco(String cep, String numero, String complemento) {
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }
}
