package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.exceptions.Status.StatusPedidoException;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.Entregador;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.situacao.Concluido;
import br.com.lamppit.teste.model.situacao.EmAtendimento;
import br.com.lamppit.teste.model.situacao.EntregaConfirmada;
import br.com.lamppit.teste.model.situacao.Entregue;
import br.com.lamppit.teste.repository.EntregadorRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static br.com.lamppit.teste.model.Status.ENTREGA_CONFIRMADA;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;


    @Autowired
    private NotFoundService notFoundService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private CepService cepService;


    @Autowired
    private ModelMapper modelMapper;

    public EntregadorDto cadastrarEntregador(EntregadorDto dto) {

        Entregador entregador = modelMapper.map(dto, Entregador.class);

        EnderecoDto enderecoDto = cepService.viaCep(dto.getEndereco());

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());

        entregador.setEndereco(endereco);
        entregador.setNome(dto.getNome());

        entregadorRepository.save(entregador);

        return modelMapper.map(entregador, EntregadorDto.class);


    }


    public EntregadorPedidoDto aceitarDelivery(PedidoIdDto dto, Long id) {

        Pedido pedido = modelMapper.map(dto, Pedido.class);

        notFoundService.sePedidoExiste(pedido.getId());
        notFoundService.seEntregadorExiste(id);

        pedido = pedidoRepository.getReferenceById(pedido.getId());
        Entregador entregador = entregadorRepository.getReferenceById(id);

        pedido.setId(pedido.getId());

        if (pedido.getSituacaoPedido() instanceof EmAtendimento) {

            pedido.setSituacaoPedido(new Concluido());
            pedido.entregaConfirmada();

            pedido.setEntregador(entregador);

            entregador.setId(id);
            entregador.setNome(entregador.getNome());
            entregador.setPedidos(Collections.singletonList(pedido));

            pedidoRepository.save(pedido);
            return modelMapper.map(pedido, EntregadorPedidoDto.class);

        }
        throw new StatusPedidoException();
    }

    public PedidoDto deliveryEntregue(Long id) {

        Pedido pedido = pedidoService.verificaPedido(id);

        if (pedido.getSituacaoPedido() instanceof Concluido) {
            pedido.setSituacaoPedido(new EntregaConfirmada());
            pedido.getSituacaoPedido().entregue(pedido);

            return pedidoService.getPedidoDto(pedido);
        }
        throw new StatusPedidoException();
    }
}
