package com.bcc.cca.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
 * referente à entidade Admin
 *
 * depois os dados são transferido la no mapper
 *
 * essas notations que estão nos atributos são do jakarta para nenhum dado vir null, se tu achar desnecessário pode tirar
 * mas se lembra de colocar no front-endd
 */

public class AdminRequestDTO {
    
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    private String phone;
    
    @NotBlank(message = "Senha é obrigatória")
    private String password;
    
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
}










