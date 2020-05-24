package io.github.sidaoswat;

import io.github.sidaoswat.domain.entity.Cliente;
import io.github.sidaoswat.domain.entity.Pedido;
import io.github.sidaoswat.domain.repository.Clientes;
import io.github.sidaoswat.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Save");
            Cliente cli = new Cliente("Sidney Rodrigues");
            clientes.save(cli);

            Pedido p = new Pedido();
            p.setCliente(cli);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            /*Cliente cliente = clientes.findClienteFetchPedidos(cli.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());*/
            pedidos.findByCliente(cli).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
