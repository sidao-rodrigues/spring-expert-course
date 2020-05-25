package io.github.sidaoswat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data //equivale a get, set entre outros mais
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao", length = 100)
    @NotEmpty(message = "Campo Descrição é obrigatório.")
    private String descricao;

    @Column(name = "preco_unitario", precision = 20, scale = 2)
    @NotNull(message = "Campo Preço é obrigatório")
    private BigDecimal preco;

}



