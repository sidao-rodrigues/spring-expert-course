package io.github.sidaoswat.domain.repository;

import io.github.sidaoswat.domain.entity.Cliente;
import io.github.sidaoswat.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> { //classe e chave da tabela
    List<Pedido> findByCliente(Cliente cliente);
}
