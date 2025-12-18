package com.bcc.cca.dto.response;

import java.time.Instant;

import com.bcc.cca.entites.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para extrair dados do sistema
 *
 * referente à entidade Payment
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

@Getter
@Setter
@NoArgsConstructor

public class PaymentResponseDTO {
    
    private Long id;
    private Instant instant;
    private PaymentMethod paymentMethod;
    private Double amount;

}










