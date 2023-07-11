package br.com.lamppit.teste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empresa extends  Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String nome;
    private String cnpj;



    @Enumerated(EnumType.STRING)
    private StatusLoja statusLoja;
    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();


    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();


    public Empresa(String nome, String cnpj, Endereco endereco, StatusLoja statusLoja) {
        super(endereco);
        this.nome = nome;
        this.cnpj = cnpj;
        this.statusLoja = statusLoja;
    }
    public Empresa(Long id ,String nome, String cnpj, Endereco endereco, StatusLoja statusLoja) {
        super(endereco);
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.statusLoja = statusLoja;
    }
}
