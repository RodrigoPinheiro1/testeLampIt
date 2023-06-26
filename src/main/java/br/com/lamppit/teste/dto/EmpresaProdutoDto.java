package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.StatusLoja;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmpresaProdutoDto {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;
    @CNPJ
    private String cnpj;

    @NotNull
    private String email;


    private StatusLoja statusLoja;

    @Valid
    private List<ProdutoDto> produtos = new ArrayList<>();

}
