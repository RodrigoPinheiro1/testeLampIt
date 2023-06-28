package br.com.lamppit.teste.model.situacao;

import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class Concluido extends SituacaoPedido {


    @Override
    public void entregaConfirmada(Pedido pedido) {
            pedido.setSituacaoPedido(new EntregaConfirmada());
            pedido.setStatus(Status.ENTREGA_CONFIRMADA);
    }

}
