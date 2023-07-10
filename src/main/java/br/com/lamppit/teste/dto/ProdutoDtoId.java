package br.com.lamppit.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDtoId {

    private Long produtoId;
}
