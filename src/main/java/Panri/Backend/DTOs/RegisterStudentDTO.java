package Panri.Backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;




@Data
public class RegisterStudentDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    @NotNull(message = "Necesita ingresar el grado del estudiante")
    private String grade;
    @NotBlank(message = "Necesita ingresar el grupo del estudiante")
    private String groupName;

}
