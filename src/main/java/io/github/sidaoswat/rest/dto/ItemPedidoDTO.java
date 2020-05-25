package io.github.sidaoswat.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {

    @NotNull(message = "Informe o código do produto.")
    private Integer produto;

    @NotNull(message = "Informe a quantidade do produto.")
    private Integer quantidade;

}
