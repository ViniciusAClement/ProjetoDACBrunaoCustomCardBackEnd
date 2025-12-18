package com.bcc.cca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para extrair dados do sistema
 *
 * referente à entidade Client
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class ClientResponseDTO {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private Set<AdressResponseDTO> adresses = new HashSet<>();
    private Set<CardInfoResponseDTO> cardInfo = new HashSet<>();

}










