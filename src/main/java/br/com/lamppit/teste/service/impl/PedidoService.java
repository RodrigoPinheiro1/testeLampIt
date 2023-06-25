package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.PedidoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.exceptions.AguardarConfirmarEntregaException;
import br.com.lamppit.teste.exceptions.EmpresaFechadaException;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Empresa;
import br.com.lamppit.teste.model.Pedido;
import br.com.lamppit.teste.model.Status;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.repository.EmpresaRepository;
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
    private NotFoundService notFoundService;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    public PedidoDto cadastrarPedido(PedidoEmpresaIdDto dto, Long idCliente) {

        Pedido pedido = modelMapper.map(dto, Pedido.class);

        notFoundService.seExisteEmpresa(dto.getEmpresaId());
        notFoundService.seClienteExiste(idCliente);

        Cliente cliente = clienteRepository.getReferenceById(idCliente);
        Empresa empresa = empresaRepository.getReferenceById(dto.getEmpresaId());

        empresaRepository.seEmpresaEstaAberto(dto.getEmpresaId()).orElseThrow(EmpresaFechadaException::new);


        pedido.setCliente(cliente);
        pedido.setEmpresa(empresa);
        pedido.setStatus(Status.CADASTRADO);
        pedido.setDataPedido(LocalDateTime.now());

        pedido.getProdutos().forEach(produto ->
        {
            notFoundService.seProdutoExiste(produto.getId());
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


        notFoundService.sePedidoExiste(id);
        Pedido pedido = pedidoRepository.getReferenceById(id);

        if (pedido.getStatus() == Status.CADASTRADO) {

            pedido.setId(id);
            pedido.setStatus(Status.EM_ATENDIMENTO);
            return getPedidoDto(pedido);
        } else if (pedido.getStatus() == Status.EM_ATENDIMENTO) {
            pedido.setId(id);
            pedido.setStatus(Status.CONCLUIDO);
            return getPedidoDto(pedido);
        } else if (pedido.getStatus() ==Status.CONCLUIDO) {
           throw new AguardarConfirmarEntregaException();
        } else if(pedido.getStatus() == Status.ENTREGA_CONFIRMADA) {
            throw new AguardarConfirmarEntregaException();
        }
        throw new AguardarConfirmarEntregaException();
    }



    private PedidoDto getPedidoDto(Pedido pedido) {
        pedido.setDataPedido(pedido.getDataPedido());
        pedido.setEntregador(pedido.getEntregador());
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
