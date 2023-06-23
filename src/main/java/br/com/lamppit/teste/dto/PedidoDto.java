package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.FormaEntrega;
import br.com.lamppit.teste.model.FormaPagamento;
import br.com.lamppit.teste.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDto {


    private Long id;

    private LocalDateTime dataPedido;

    private Status status;

    private String formaPagamento;

    private String formaEntrega;
    private List<ProdutoDto> produtos = new ArrayList<>();

}
