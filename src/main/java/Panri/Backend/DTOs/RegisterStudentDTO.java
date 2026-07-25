package Panri.Backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;




@Data
public class RegisterStudentDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    @NotBlank(message = "La matricula del alumno es obligatoria")
    private String enrollmentCode;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 12, message = "la contraseña debe tener entre 8 y 12 caracteres")
    private String password;

}
