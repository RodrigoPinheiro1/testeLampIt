package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import br.com.lamppit.teste.repository.PedidoRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class PedidoService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDto cadastrarPedido(PedidoDto dto) {

        Pedido pedido = modelMapper.map(dto, Pedido.class);

        pedido.setStatus(Status.ANDAMENTO);
        pedido.setDataPedido(LocalDateTime.now());

        pedido.getProdutos().forEach(produto ->
        {
            produto = produtoRepository.getReferenceById(produto.getId());
            produto.setPedido(pedido);
            pedido.setProdutos(Collections.singletonList(produto));
        });

        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoDto.class);

    }

    public Page<PedidoDto> pedidosStatusEmAndamento(Pageable pageable) {

        return pedidoRepository.pedidosStatusEmAndamento(pageable)
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class));
    }

    public Page<PedidoDto> pedidosDisponiveisEntrega(Pageable pageable) {

        return pedidoRepository.pedidosDisponiveisEntrega(pageable)
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class));
    }




    public PedidoDto atualizarPedidoParaEmAtendimento(Long id) {


        Pedido pedido = pedidoRepository.getReferenceById(id);

        pedido.setId(id);
        pedido.setStatus(Status.EM_ATENDIMENTO);

        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoDto.class);
    }


    public PedidoDto atualizarPedidoConcluido(Long id) {


        Pedido pedido = pedidoRepository.getReferenceById(id);

        pedido.setId(id);
        pedido.setStatus(Status.CONCLUIDO);

        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoDto.class);
    }

    public PedidoDto atualizarPedidoParaEntregue(Long id) {

        Pedido pedido = pedidoRepository.getReferenceById(id);

        pedido.setId(id);
        pedido.setStatus(Status.ENTREGUE);

        return modelMapper.map(pedido,PedidoDto.class);
    }
    public Page<PedidoDto> pedidosNaoEntregues(Pageable pageable) {

        return pedidoRepository.pedidosStatusNaoEntregue(pageable)
                .map(pedido -> modelMapper.map(pedido,PedidoDto.class));

    }


    public Page<PedidoDto> pedidos(Pageable pageable) {

        return pedidoRepository.findAll(pageable).map(pedido -> modelMapper.map(pedido,PedidoDto.class));
    }
}
