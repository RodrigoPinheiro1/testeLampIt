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
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Endereco endereco,String nome) {


        super(endereco);
        this.nome = nome;

    }
    public Cliente(Long id,Endereco endereco,String nome) {

        super(endereco);
        this.id = id;
        this.nome = nome;

    }
}
