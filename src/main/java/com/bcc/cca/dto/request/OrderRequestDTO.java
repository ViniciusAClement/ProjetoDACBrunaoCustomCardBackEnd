package com.bcc.cca.dto.request;

import com.bcc.cca.entites.enumeration.DeliveryStatus;
import com.bcc.cca.entites.enumeration.PaymentStatus;

import jakarta.validation.constraints.NotNull;
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
 * referente à entidade Order
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class OrderRequestDTO {
    
    private Long id;
    private PaymentStatus paymentStatus;
    private DeliveryStatus deliveryStatus;
    
    @NotNull(message = "Cliente é obrigatório")
    private Long clientId;
    
    private PaymentRequestDTO payment;
}










