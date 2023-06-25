package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.FormaEntrega;
import br.com.lamppit.teste.model.FormaPagamento;
import br.com.lamppit.teste.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoEmpresaIdDto {


    private Long id;

    private LocalDateTime dataPedido;

    private Status status;
    @NotNull
    private FormaPagamento formaPagamento;
    @NotNull
    private FormaEntrega formaEntrega;

    @NotNull
    private Long empresaId;


    @NotNull
    @Valid
    private List<ProdutoDtoId> produtos = new ArrayList<>();

}
