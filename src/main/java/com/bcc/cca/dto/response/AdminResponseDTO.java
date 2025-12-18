package com.bcc.cca.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para extrair dados do sistema
 *
 * referente à entidade Admin
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class AdminResponseDTO {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
}










