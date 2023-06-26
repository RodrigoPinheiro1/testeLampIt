package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.StatusLoja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {


    private Long id;
    @NotNull
    @NotEmpty
    private String nome;

    @CNPJ
    private String cnpj;
    @NotNull
    @NotEmpty
    private String email;

    private StatusLoja statusLoja;



}
