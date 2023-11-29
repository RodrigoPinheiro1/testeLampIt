package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.exceptions.EmpresaFechadaException;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.situacao.Cadastrado;
import br.com.lamppit.teste.model.situacao.EmAnalise;
import br.com.lamppit.teste.model.situacao.EmAtendimento;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.repository.EmpresaRepository;
import br.com.lamppit.teste.repository.PedidoRepository;
import br.com.lamppit.teste.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {


    private final ModelMapper modelMapper;
    private final FinByIdService finByIdService;
    private final ProdutoRepository produtoRepository;

    private final EmpresaRepository empresaRepository;

    private final ClienteRepository clienteRepository;

    private final PedidoRepository pedidoRepository;


    public PedidoDto cadastrarPedido(PedidoEmpresaIdDto dto, Long idCliente) {

        Pedido pedido = modelMapper.map(dto, Pedido.class);

        Empresa empresa = finByIdService.seExisteEmpresa(dto.getEmpresaId());
        Cliente cliente = finByIdService.seClienteExiste(idCliente);


        empresaRepository.seEmpresaEstaAberto(dto.getEmpresaId()).orElseThrow(EmpresaFechadaException::new);

        pedido.setSituacaoPedido(new EmAnalise());
        pedido.cadastro();

        pedido.setCliente(cliente);
        pedido.setEmpresa(empresa);
        pedido.setDataPedido(LocalDateTime.now());

        pedido.getProdutos().forEach(produto ->
        {
            finByIdService.seProdutoExiste(produto.getId());
            produto = produtoRepository.getReferenceById(produto.getId());
            produto.setPedido(pedido);
            pedido.setProdutos(List.of(produto));
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


    public PedidoDto atualizaPedidoParaConcluido(Long id) {


        Pedido pedido = verificaPedido(id);
        pedido.setSituacaoPedido(new EmAtendimento());
        pedido.concluido();

        return getPedidoDto(pedido);
    }

    public PedidoDto atualizarPedidoParaEmAtendimento(Long id) {

        Pedido pedido = verificaPedido(id);

        pedido.setSituacaoPedido(new Cadastrado());
        pedido.emAtendimento();

        return getPedidoDto(pedido);
    }


    public Pedido verificaPedido(Long id) {
        return finByIdService.sePedidoExiste(id);

    }


    public PedidoDto getPedidoDto(Pedido pedido) {
        pedido.setDataPedido(pedido.getDataPedido());
        pedido.setEntregador(pedido.getEntregador());
        pedido.setCliente(pedido.getCliente());
        pedido.setEmpresa(pedido.getEmpresa());
        pedido.setFormaEntrega(pedido.getFormaEntrega());
        pedido.setFormaPagamento(pedido.getFormaPagamento());

        pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoDto.class);
    }


    public Page<PedidoDto> pedidosNaoEntregues(Pageable pageable) {

        return pedidoRepository.pedidosStatusNaoEntregue(pageable)
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class));

    }


    public Page<PedidoDto> pedidos(Pageable pageable) {

        return pedidoRepository.findAll(pageable).map(pedido -> modelMapper.map(pedido, PedidoDto.class));
    }
}
