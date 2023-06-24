package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.Empresa;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoEmpresaDto {

    private Long produtoId;

    @NotNull
    private String nome;

    @NotNull
    private Integer quantidade;

    @NotNull
    private String descricao;

    @NotNull
    private EmpresaDto empresa;



}
