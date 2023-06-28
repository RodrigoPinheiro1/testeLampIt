package br.com.lamppit.teste.model.situacao;

import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class EntregaConfirmada extends SituacaoPedido {


    public void entregue(Pedido pedido) {

            pedido.setSituacaoPedido(new Entregue());
            pedido.setStatus(Status.ENTREGUE);
    }
}
