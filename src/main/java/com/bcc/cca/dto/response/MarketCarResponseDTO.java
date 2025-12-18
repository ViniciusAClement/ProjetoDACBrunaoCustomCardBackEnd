package com.bcc.cca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para extrair dados do sistema
 *
 * referente à entidade MarketCar
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class MarketCarResponseDTO {
    
    private Long id;
    private ClientResponseDTO client;
    private Set<MarketCarItemResponseDTO> marketCarItens = new HashSet<>();

}










