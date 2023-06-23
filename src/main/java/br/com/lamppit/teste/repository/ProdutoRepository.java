package br.com.lamppit.teste.repository;

import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


}
