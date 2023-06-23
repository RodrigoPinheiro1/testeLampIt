package br.com.lamppit.teste.repository;

import br.com.lamppit.teste.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


    Page<Empresa> findAllById(Long id, Pageable pageable);



}
