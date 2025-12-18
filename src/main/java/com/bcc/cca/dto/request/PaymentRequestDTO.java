package com.bcc.cca.dto.request;

import com.bcc.cca.entites.enumeration.PaymentMethod;

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
 * referente à entidade Payment
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class PaymentRequestDTO {
    
    private Long id;
    
    @NotNull(message = "Método de pagamento é obrigatório")
    private PaymentMethod paymentMethod;
    
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private Double amount;

}










