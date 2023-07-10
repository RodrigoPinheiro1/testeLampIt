package br.com.lamppit.teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer quantidade;

    private String descricao;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Pedido pedido;


    public Produto(Long id, String nome, Integer quantidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Produto(Long id, String nome, Integer quantidade, String descricao, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.empresa = empresa;
    }
}
