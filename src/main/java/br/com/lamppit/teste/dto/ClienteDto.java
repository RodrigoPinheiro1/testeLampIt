package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;

    @Valid
    @NotNull
    private EnderecoDto endereco;


}
