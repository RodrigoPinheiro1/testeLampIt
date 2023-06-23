package br.com.lamppit.teste.repository;

import br.com.lamppit.teste.model.Entregador;
import br.com.lamppit.teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {


    


}
