package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.StatusLoja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {


    private Long id;
    private String nome;
    private String cnpj;

    private StatusLoja statusLoja;



}
