package io.github.sidaoswat.rest.controller;

import io.github.sidaoswat.domain.entity.Cliente;
import io.github.sidaoswat.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clientes clientes;

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id){
        return clientes
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id){
         clientes.findById(id)
            .map( cliente -> {
                clientes.delete(cliente);
                return cliente;
            })
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable("id") Integer id, @RequestBody @Valid Cliente cliente){
        clientes.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return cliente;
                }).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase() //serve para ignorar caixa alta ou baixa
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //procura em qualquer posição da string
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
