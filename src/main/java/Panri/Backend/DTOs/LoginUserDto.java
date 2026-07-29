package Panri.Backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message = "Debe de ingresar un usuario")
    private String username;
    @NotBlank(message = "Debe de ingresar una contraseña")
    private String password;

}
