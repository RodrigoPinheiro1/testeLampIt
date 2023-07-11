package br.com.lamppit.teste.dto;

import br.com.lamppit.teste.model.FormaEntrega;
import br.com.lamppit.teste.model.FormaPagamento;
import br.com.lamppit.teste.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEmpresaIdDto {


    private Long id;


    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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

    public PedidoEmpresaIdDto(FormaPagamento formaPagamento, FormaEntrega formaEntrega, Long empresaId, List<ProdutoDtoId> produtos) {

        this.formaPagamento = formaPagamento;
        this.formaEntrega = formaEntrega;
        this.empresaId = empresaId;
        this.produtos = produtos;
    }
}
