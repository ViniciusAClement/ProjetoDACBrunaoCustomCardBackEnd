package com.bcc.cca.dto.response;

import com.bcc.cca.entites.enumeration.CardType;
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
 * referente à entidade CardInfo
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class CardInfoResponseDTO {
    
    private Long id;
    private String cardNumber;
    private String creditCardOwner;
    private CardType cardType;

}










