package io.github.sidaoswat.service.impl;

import io.github.sidaoswat.domain.repository.Pedidos;
import io.github.sidaoswat.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImp implements PedidoService {

    @Autowired
    private Pedidos repository;

}
