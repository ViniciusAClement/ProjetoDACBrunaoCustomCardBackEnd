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
 * referente à entidade Product
 *
 * tipo assim, você faz uma requisição de dados e ele retorna somente os dados necessários para você, ocultantdo informações sensíveis
 */

public class ProductResponseDTO {
    
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<CategoryResponseDTO> categories = new HashSet<>();
    private Set<CarResponseDTO> cars = new HashSet<>();

}










