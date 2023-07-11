package br.com.lamppit.teste.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProdutoIdDto {

    @NotNull
    private Long produtoId;


}
