package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.EntregadorDto;
import br.com.lamppit.teste.dto.EntregadorPedidoDto;
import br.com.lamppit.teste.dto.PedidoIdDto;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.model.Entregador;
import br.com.lamppit.teste.model.Pedido;
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
        pedido.setStatus(ENTREGA_CONFIRMADA);
        pedido.setEntregador(entregador);

        entregador.setId(id);
        entregador.setNome(entregador.getNome());
        entregador.setPedidos(Collections.singletonList(pedido));

        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, EntregadorPedidoDto.class);


    }
}
