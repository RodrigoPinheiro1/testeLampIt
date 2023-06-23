package br.com.lamppit.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDto {


    private Long id;
    private String nome;
    private String cnpj;



}
