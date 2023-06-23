package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmpresaProdutoDto {

    private Long id;
    private String nome;
    private String cnpj;
    private List<ProdutoDto> produtos = new ArrayList<>();

}
