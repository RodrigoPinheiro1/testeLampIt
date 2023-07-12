package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.StatusLoja;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaProdutoDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;
    @CNPJ
    private String cnpj;

    @NotNull
    private String email;


    @Valid
    @NotNull
    private EnderecoDto endereco;
    private StatusLoja statusLoja;

    @Valid
    private List<ProdutoDto> produtos = new ArrayList<>();

}
