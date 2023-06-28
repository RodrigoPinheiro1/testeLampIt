package br.com.lamppit.teste.model.situacao;


import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Pedido;

import javax.persistence.Embeddable;

@Embeddable
public abstract class SituacaoPedido {


    public void cadastrado(Pedido pedido) {
        throw new StatusPedidoException();
    }

    public void emAtendimento(Pedido pedido) {
        throw new StatusPedidoException();
    }

    public void concluido(Pedido pedido) {
        throw new StatusPedidoException();
    }

    public void entregaConfirmada(Pedido pedido) {
        throw new StatusPedidoException();
    }

    public void entregue(Pedido pedido) {
        throw new StatusPedidoException();

    }
}
