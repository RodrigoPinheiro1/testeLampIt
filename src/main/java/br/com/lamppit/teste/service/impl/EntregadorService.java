package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.*;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.Entregador;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.situacao.Concluido;
import br.com.lamppit.teste.model.situacao.EntregaConfirmada;
import br.com.lamppit.teste.repository.EntregadorRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    private final FinByIdService finByIdService;
    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;
    private final CepService cepService;

    private final ModelMapper modelMapper;

    public EntregadorDto cadastrarEntregador(EntregadorDto dto) {

        Entregador entregador = modelMapper.map(dto, Entregador.class);

        EnderecoDto enderecoDto = cepService.viaCep(dto.getEndereco()).block();

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());

        entregador.setEndereco(endereco);
        entregador.setNome(dto.getNome());

        entregadorRepository.save(entregador);

        return modelMapper.map(entregador, EntregadorDto.class);


    }


    public EntregadorPedidoDto aceitarDelivery(PedidoIdDto dto, Long idEntregador) {

        Pedido pedido = modelMapper.map(dto, Pedido.class);


        Entregador entregador = finByIdService.seEntregadorExiste(idEntregador);

        pedido =  finByIdService.sePedidoExiste(pedido.getId());

        pedido.setId(pedido.getId());

        pedido.setSituacaoPedido(new Concluido());
        pedido.entregaConfirmada();

        pedido.setEntregador(entregador);

        entregador.setId(idEntregador);
        entregador.setNome(entregador.getNome());
        entregador.setPedidos(Collections.singletonList(pedido));

        pedidoRepository.save(pedido);
        return modelMapper.map(pedido, EntregadorPedidoDto.class);
    }


    public PedidoDto deliveryEntregue(Long id) {

        Pedido pedido = pedidoService.verificaPedido(id);

        pedido.setSituacaoPedido(new EntregaConfirmada());
        pedido.entregue();

        return pedidoService.getPedidoDto(pedido);

    }
}
