package br.com.lamppit.teste.model.situacao;

import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Cadastrado extends SituacaoPedido{


    @Override
    public void emAtendimento(Pedido pedido) {
        if (pedido.getStatus() == Status.CADASTRADO) {

            pedido.setSituacaoPedido(new EmAtendimento());
            pedido.setStatus(Status.EM_ATENDIMENTO);
        }
        else {
            throw new StatusPedidoException();
        }
    }

}
