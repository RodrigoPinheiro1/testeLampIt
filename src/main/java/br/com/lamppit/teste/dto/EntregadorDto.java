package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EntregadorDto {

    private Long id;

    @NotNull
    private String nome;


}
