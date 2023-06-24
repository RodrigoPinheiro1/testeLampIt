package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoDto {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Integer quantidade;

    @NotNull
    private String descricao;




}
