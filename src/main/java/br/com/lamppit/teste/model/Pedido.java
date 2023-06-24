package br.com.lamppit.teste.model;

import br.com.lamppit.teste.dto.EntregadorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private Status status;

   // @Enumerated(EnumType.STRING)
    private String formaPagamento;

   // @Enumerated(EnumType.STRING)
    private String formaEntrega;

    @ManyToOne
    private Entregador entregador;


    @ManyToOne
    private Empresa empresa;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pedido")
    private List<Produto> produtos = new ArrayList<>();



}
