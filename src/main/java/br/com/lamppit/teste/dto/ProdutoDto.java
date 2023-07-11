package br.com.lamppit.teste.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


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


    public ProdutoDto(Long id, String nome, Integer quantidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public ProdutoDto(String nome, Integer quantidade, String descricao) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }
}
