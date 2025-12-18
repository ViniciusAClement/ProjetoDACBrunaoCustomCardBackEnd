package com.bcc.cca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
 * referente à entidade Car
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class CarRequestDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    @NotNull(message = "Ano é obrigatório")
    private Integer yearOfCar;
    
    private Long carBrandId;

}










