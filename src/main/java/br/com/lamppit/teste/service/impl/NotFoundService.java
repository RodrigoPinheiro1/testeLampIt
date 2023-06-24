package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.exceptions.ProductNotFound;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.EntregadorRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
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

    public void seExisteEmpresa(Long id) {

        empresaRepository.findById(id).orElseThrow(ProductNotFound::new);

    }
    public void seProdutoExiste(Long id) {
        produtoRepository.findById(id).orElseThrow(ProductNotFound::new);

    }

    public void seEntregadorExiste(Long id) {
        entregadorRepository.findById(id).orElseThrow(ProductNotFound::new);

    }

    public void sePedidoExiste(Long id) {

        pedidoRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

}
