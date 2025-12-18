package com.bcc.cca.dto.response;

import java.time.Instant;

import com.bcc.cca.entites.enumeration.DeliveryStatus;
import com.bcc.cca.entites.enumeration.PaymentStatus;
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
 * classe para extrair dados do sistema
 *
 * referente à entidade ORder
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class OrderResponseDTO {
    
    private Long id;
    private PaymentStatus paymentStatus;
    private DeliveryStatus deliveryStatus;
    private Instant instant;
    private ClientResponseDTO client;
    private PaymentResponseDTO payment;

}










