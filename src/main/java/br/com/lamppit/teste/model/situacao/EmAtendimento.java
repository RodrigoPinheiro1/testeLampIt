package br.com.lamppit.teste.model.situacao;

import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class EmAtendimento extends SituacaoPedido {


    @Override
    public void concluido(Pedido pedido) {
        if (pedido.getStatus() == Status.EM_ATENDIMENTO) {
            pedido.setSituacaoPedido(new Concluido());
            pedido.setStatus(Status.CONCLUIDO);
        }else {
            throw new StatusPedidoException();
        }
    }

}
