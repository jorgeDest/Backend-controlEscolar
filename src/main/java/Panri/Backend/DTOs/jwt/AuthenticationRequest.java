package Panri.Backend.DTOs.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotBlank(message = "Debe de ingresar un usuario")
    private String username;
    @NotBlank(message = "Debe de ingresar una contraseña")
    private String password;

}
