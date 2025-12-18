package com.bcc.cca.dto.request;

import com.bcc.cca.entites.enumeration.CardType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para entrar dados no sistema
 *
 * referente à entidade CardInfo
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class CardInfoRequestDTO {
    
    private Long id;
    
    @NotBlank(message = "Número do cartão é obrigatório")
    private String cardNumber;
    
    @NotBlank(message = "Proprietário do cartão é obrigatório")
    private String creditCardOwner;
    
    @NotNull(message = "Tipo do cartão é obrigatório")
    private CardType cardType;
    
    @NotNull(message = "Cliente é obrigatório")
    private Long clientId;
}










