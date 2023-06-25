package br.com.lamppit.teste.repository;

import br.com.lamppit.teste.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


    Page<Empresa> findAllById(Long id, Pageable pageable);


    @Query("SELECT e from Empresa e where size(e.produtos) > 0 ")
    Page<Empresa> acharEmpresasQueTenhamProdutos(Pageable pageable);

    @Query("select e from Empresa e where e.id =:id and e.statusLoja = 'ABERTO'")
    Optional<Empresa> seEmpresaEstaAberto(Long id);
}
