package br.com.lamppit.teste.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntregadorPedidoDto {

    private Long entregadorId ;

    private String entregadorNome;

    private PedidoDto pedido;


}
