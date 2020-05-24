package io.github.sidaoswat.service;

import io.github.sidaoswat.domain.entity.Pedido;
import io.github.sidaoswat.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

}
