package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.exceptions.NotFound.*;
import br.com.lamppit.teste.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotFoundService {


    @Autowired
    private EntregadorRepository entregadorRepository;


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void seExisteEmpresa(Long id) {

        empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new);

    }
    public void seProdutoExiste(Long id) {
        produtoRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    }

    public void seEntregadorExiste(Long id) {
        entregadorRepository.findById(id).orElseThrow(EntregadorNotFoundException::new);

    }

    public void sePedidoExiste(Long id) {

        pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
    }
    public void seClienteExiste(Long id) {

        clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

}
