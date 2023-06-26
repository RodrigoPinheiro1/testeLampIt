package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.dto.PedidoEmpresaIdDto;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.repository.ClienteRepository;
import br.com.lamppit.teste.service.impl.NotFoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private NotFoundService notFoundService;

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private CepService cepService;

    @Autowired
    private ModelMapper modelMapper;


    public ClienteDto cadastrarCliente(ClienteDto dto) {

        Cliente cliente = modelMapper.map(dto, Cliente.class);

        EnderecoDto enderecoDto = cepService.viaCep(dto.getEndereco());

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteDto.class);
    }


}
