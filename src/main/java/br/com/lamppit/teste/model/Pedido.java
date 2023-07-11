package br.com.lamppit.teste.model;

import br.com.lamppit.teste.model.situacao.SituacaoPedido;
import jakarta.persistence.*;
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
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private Status status;


    // @Enumerated(EnumType.STRING)
    @NotNull
    private FormaPagamento formaPagamento;
    //  @Enumerated(EnumType.STRING)
    @NotNull
    private FormaEntrega formaEntrega;

    @Embedded
    private SituacaoPedido situacaoPedido;

    @ManyToOne
    private Entregador entregador;

    @ManyToOne
    private Cliente cliente;


    @ManyToOne
    private Empresa empresa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Produto> produtos = new ArrayList<>();


    public void cadastro() {
        this.situacaoPedido.cadastrado(this);
    }

    public void concluido() {
        situacaoPedido.concluido(this);
    }

    public void emAtendimento() {
        situacaoPedido.emAtendimento(this);
    }

    public void entregaConfirmada() {
        situacaoPedido.entregaConfirmada(this);
    }

    public void entregue() {
        situacaoPedido.entregue(this);
    }


}
