package Panri.Backend.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


public record ReportCardResponseDTO(
        Long reportCardId,
        StudentDTO student,
        LocalDate termName,
        List<DetailDTO> details
) {
    public record StudentDTO(
            Long studentId,
            String firstName,
            String lastName,
            String enrollmentCode,
            String groupName
    ) {
    }

    public record DetailDTO(
            Long detailId,
            Integer score,
            String subjectName,
            String teacherName
    ) {
    }
}