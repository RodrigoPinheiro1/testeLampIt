package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.exceptions.NotFound.*;
import br.com.lamppit.teste.model.*;
import br.com.lamppit.teste.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinByIdService {


    private final EntregadorRepository entregadorRepository;


    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    private final EmpresaRepository empresaRepository;


    private final ClienteRepository clienteRepository;

    public Empresa seExisteEmpresa(Long id) {

        return empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new);

    }

    public Produto seProdutoExiste(Long id) {

        return produtoRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    }

    public Entregador seEntregadorExiste(Long id) {
        return entregadorRepository.findById(id).orElseThrow(EntregadorNotFoundException::new);

    }

    public Pedido sePedidoExiste(Long id) {

        return pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
    }

    public Cliente seClienteExiste(Long id) {

        return clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

}
