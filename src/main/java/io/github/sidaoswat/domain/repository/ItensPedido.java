package io.github.sidaoswat.domain.repository;

import io.github.sidaoswat.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
