package Panri.Backend.DTOs.jwt;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationRequest {
    @NotBlank(message = "Debe de ingresar un usuario")
    private String username;
    @NotBlank(message = "Debe de ingresar una contraseña")
    private String password;

}
