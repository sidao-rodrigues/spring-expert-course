package io.github.sidaoswat.domain.repositorio;

import io.github.sidaoswat.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository //no JpaRepository não há a necessidade de colocar essa anotação
public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = " select cli from Cliente cli where cli.nome like :nome ")//pode ser com SQL(nativeQuery=true) ou HQL
    List<Cliente> encontrarPorNome( @Param("nome") String nome );
    //List<Cliente> findByNomeLike(String nome);

    @Query(" delete from Cliente cli where cli.nome =:nome")
    @Modifying
    void deleteByNome(String nome);

    //List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
    //Cliente findOneById(Integer id); //bom exemplo para cpf
    //boolean existsByNome(String nome);

}
