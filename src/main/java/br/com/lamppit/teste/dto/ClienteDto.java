package br.com.lamppit.teste.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;

    @Valid
    @NotNull
    private EnderecoDto endereco;


    public ClienteDto(EnderecoDto endereco,String nome) {
        this.nome = nome;
        this.endereco = endereco;
    }
}
