package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoIdDto {

    @NotNull
    private Long produtoId;


}
