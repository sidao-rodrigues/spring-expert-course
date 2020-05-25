package io.github.sidaoswat.service;

import io.github.sidaoswat.domain.entity.Pedido;
import io.github.sidaoswat.domain.enums.StatusPedido;
import io.github.sidaoswat.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizarStatus(Integer id, StatusPedido statusPedido);

}
