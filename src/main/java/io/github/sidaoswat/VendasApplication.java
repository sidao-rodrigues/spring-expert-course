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
            clientes.salvar(new Cliente("Sidão"));
            clientes.salvar(new Cliente("Sidney Rodrigues"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            /*
            System.out.println("update");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " att");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("search");
            clientes.buscarPorNome("ney").forEach(System.out::println);

            System.out.println("delete");
            /*clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });*/
            /*
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()) {
                System.out.println("nenhum cliente encontrado!");
            } else {
                todosClientes.forEach(System.out::println);
            }*/
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
