package br.com.lamppit.teste.service.impl;

import br.com.lamppit.teste.dto.ClienteDto;
import br.com.lamppit.teste.dto.EnderecoDto;
import br.com.lamppit.teste.model.Cliente;
import br.com.lamppit.teste.model.Endereco;
import br.com.lamppit.teste.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final FinByIdService finByIdService;

    private final ClienteRepository clienteRepository;

    private final CepService cepService;

    private final ModelMapper modelMapper;


    public ClienteDto cadastrarCliente(ClienteDto dto) {

        Cliente cliente = modelMapper.map(dto, Cliente.class);

        EnderecoDto enderecoDto = cepService.viaCep(dto.getEndereco()).block();

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setNumero(dto.getEndereco().getNumero());
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
        return modelMapper.map(cliente, ClienteDto.class);
    }


}
