package br.com.lamppit.teste.service;


import br.com.lamppit.teste.dto.EmpresaDto;
import br.com.lamppit.teste.dto.EmpresaProdutoDto;
import br.com.lamppit.teste.dto.ListProdutoDto;

public interface EmpresaService {




    EmpresaProdutoDto cadastrarEmpresa(EmpresaProdutoDto empresaDto);

    EmpresaProdutoDto cadastrarProdutoEmpresa(ListProdutoDto dto, Long id);
}
