package br.com.lamppit.teste.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto {

    private Long produtoId;

    private String nome;

    private Integer quantidade;

    private String descricao;



}
