package io.github.sidaoswat.rest.controller;

import io.github.sidaoswat.domain.entity.Produto;
import io.github.sidaoswat.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private Produtos produtos;

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Integer id){
        return produtos.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos.findById(id)
                .map(produto -> {
                    produtos.delete(produto);
                    return produto;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid Produto produto){/*refatorar para retonar depois um produto e para cliente*/
        produtos.findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtos.save(produto);
                    return produto;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtos.findAll(example);
    }
}
