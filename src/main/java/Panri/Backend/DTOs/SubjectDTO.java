package Panri.Backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {
    @NotBlank(message = "Debe de ingresar un nombre para la materia")
    private String subjectName;
    @NotBlank(message = "Debe de ingresar un código para la materia")
    private String subjectCode;

}
