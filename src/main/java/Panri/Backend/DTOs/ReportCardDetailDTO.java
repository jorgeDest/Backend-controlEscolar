package Panri.Backend.DTOs;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ReportCardDetailDTO {


    /*Lo que nos interesa que pedir en el DTO es el nombre del alumno, la materia calificada y su código,
     fecha, grupo, grado y periodo al que pertenece*/

    @NotNull(message = "Debe de ingresar una calificación")
    @Max(value = 10, message = "La calificación valida no puede superar el 10")
    @Min(value = 0, message = "La calificación minima no debe ser menor de cero")
    private Integer score;

    @NotNull(message = "Debe especificar la materia")
    private Long subjectId;

    @NotNull(message = "Debe especificar el semestre")
    private Long reportCardId;


}
