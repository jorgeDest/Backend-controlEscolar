package Panri.Backend.DTOs;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseCardDetailDTO {

    private Integer score;
    private String subjectName;
    private String subjectCode;
    private String grade;
    private String level;
    private LocalDate termName;

}
