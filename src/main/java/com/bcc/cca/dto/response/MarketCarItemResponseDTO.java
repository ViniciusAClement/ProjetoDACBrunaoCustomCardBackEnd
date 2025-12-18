package com.bcc.cca.dto.response;

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
 * referente à entidade MarketCarItem
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class MarketCarItemResponseDTO {
    
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductResponseDTO product;

}










