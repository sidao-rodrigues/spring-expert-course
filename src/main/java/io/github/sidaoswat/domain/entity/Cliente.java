package io.github.sidaoswat.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity //essa anotação servirá para ser escaneada pelo JPA e registrar essa entidade(classe) como uma tabela no banco de dados
@Table(name = "cliente")//, schema = "vendas") //essa anotação é para o caso a tabela do bd seja diferente do nome da classe
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //essa anotação serve para identificar a coluna da tabela, é opcional mas caso o nome seja diferente tem que se utilizar
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) //é mapeado com o atributo em pedido de cliente
    private Set<Pedido> pedidos;

    public Cliente(){
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente(String nome){
        this.nome = nome;
    }

    public Cliente(String nome, Integer id){
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
