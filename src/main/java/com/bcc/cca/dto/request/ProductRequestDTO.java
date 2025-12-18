package com.bcc.cca.dto.request;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Bora lá vou repetir isso em todas do request pra n perder a explicação
 *
 * classe para entrar dados no sistema
 *
 * referente à entidade Product
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class ProductRequestDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    private String description;
    
    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private Double price;
    
    private String imgUrl;
    
    private Set<Long> categoryIds = new HashSet<>();
    
    private Set<Long> carIds = new HashSet<>();

}










