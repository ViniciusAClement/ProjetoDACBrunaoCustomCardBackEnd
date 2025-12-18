package com.bcc.cca.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para entrar dados no sistema
 *
 * referente à entidade MarketCarItem
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class MarketCarItemRequestDTO {
    
    private Long id;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    private Integer quantity;
    
    @NotNull(message = "Produto é obrigatório")
    private Long productId;
    
    private Long marketCarId;

}










