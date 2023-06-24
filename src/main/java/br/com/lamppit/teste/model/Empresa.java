package br.com.lamppit.teste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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


    private String cnpj;

    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();


    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

}
