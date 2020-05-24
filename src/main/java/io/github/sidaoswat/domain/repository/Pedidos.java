package io.github.sidaoswat.domain.repository;

import io.github.sidaoswat.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> { //classe e chave da tabela
}
