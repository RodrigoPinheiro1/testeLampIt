package br.com.lamppit.teste.model.situacao;

import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import org.springframework.stereotype.Component;

@Component

public class EmAnalise extends  SituacaoPedido {


    @Override
    public void cadastrado(Pedido pedido){
        pedido.setSituacaoPedido(new Cadastrado());
        pedido.setStatus(Status.CADASTRADO);
    }
}
