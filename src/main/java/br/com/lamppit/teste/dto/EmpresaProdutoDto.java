package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmpresaProdutoDto {

    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String cnpj;

    @Valid
    @NotNull
    private List<ProdutoDto> produtos = new ArrayList<>();

}
