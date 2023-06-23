package br.com.lamppit.teste.repository;

import br.com.lamppit.teste.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {




    @Query("select p from Pedido p where p.status = 'ANDAMENTO'")
    Page<Pedido> pedidosStatusEmAndamento(Pageable pageable);

    @Query("select p from Pedido p where p.status = 'Concluido'")
    Page<Pedido> pedidosDisponiveisEntrega(Pageable pageable);

    @Query("select p from Pedido p where p.status != 'ENTREGUE'")
    Page<Pedido> pedidosStatusNaoEntregue(Pageable pageable);
}
