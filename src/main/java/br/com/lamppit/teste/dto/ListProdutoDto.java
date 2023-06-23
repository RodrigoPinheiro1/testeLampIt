package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ListProdutoDto {

    private List<ProdutoDto> produtos = new ArrayList<>();
}
