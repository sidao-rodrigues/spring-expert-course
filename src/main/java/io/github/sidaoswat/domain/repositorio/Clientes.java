package io.github.sidaoswat.domain.repositorio;

import io.github.sidaoswat.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {
    
    List<Cliente> findByNomeLike(String nome);
}
