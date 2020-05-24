package io.github.sidaoswat;

import io.github.sidaoswat.domain.entity.Cliente;
import io.github.sidaoswat.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Save");
            clientes.save(new Cliente("Sidão"));
            clientes.save(new Cliente("Sidney Rodrigues"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);


            System.out.println("update");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " att");
                clientes.save(c);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("search");
            clientes.findByNomeLike("ney").forEach(System.out::println);

            System.out.println("delete");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.findAll();
            if(todosClientes.isEmpty()) {
                System.out.println("nenhum cliente encontrado!");
            } else {
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
