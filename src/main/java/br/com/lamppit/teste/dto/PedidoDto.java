package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.Entregador;
import br.com.lamppit.teste.model.FormaEntrega;
import br.com.lamppit.teste.model.FormaPagamento;
import br.com.lamppit.teste.model.Status;
import br.com.lamppit.teste.model.situacao.SituacaoPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDto {


    private Long id;

    private LocalDateTime dataPedido;


    private Status status;
    @NotNull
    private FormaPagamento formaPagamento;
    @NotNull
    private FormaEntrega formaEntrega;

    private EntregadorDto entregador;

    private EmpresaDto empresa;

    private ClienteDto cliente;


    @NotNull
    @Valid
    private List<ProdutoDtoId> produtos = new ArrayList<>();

}
