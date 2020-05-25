package io.github.sidaoswat.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }

}
