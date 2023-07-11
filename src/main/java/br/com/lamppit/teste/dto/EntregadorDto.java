package br.com.lamppit.teste.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EntregadorDto {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @Valid
    @NotNull
    private EnderecoDto endereco;


    private Long perfilId;


}
