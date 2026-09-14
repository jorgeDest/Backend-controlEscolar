package Panri.Backend.service;

import Panri.Backend.DTOs.ReportCardResponseDTO;
import Panri.Backend.model.ReportCardEntity;
import Panri.Backend.model.StudentEntity;
import Panri.Backend.repository.ReportCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReportCardService {

    @Autowired
    private ReportCardRepository reportCardRepository;

    //Buscar cuales son los report pertenecientes al student id = 6;
    public ReportCardResponseDTO getReportCardById(Long reportCardId){

        ReportCardEntity reportCard = reportCardRepository.findByReportCardId(reportCardId)
                .orElseThrow(() -> new RuntimeException("Error: Boleta no encontrada con ID: " + reportCardId));

        // 2. Extraer el estudiante
        StudentEntity student = reportCard.getStudent();
        var studentDto = new ReportCardResponseDTO.StudentDTO(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEnrollmentCode(),
                //es un ternario el cual pregunta si el alumno cuenta con un grupo de lo contrario null
                student.getStudentGroup() != null ? student.getStudentGroup().getName() : null
        );

        // 3. Transformar la lista de entidades detalle a lista de DTOs detalle
        List<ReportCardResponseDTO.DetailDTO> detailsDto = reportCard.getReportCardDetails().stream()
                .map(detail -> new ReportCardResponseDTO.DetailDTO(
                        detail.getReportCardDetail(),
                        detail.getScore(),
                        detail.getSubjectEntity() != null ? detail.getSubjectEntity().getName() : null, // Ajusta los getters según tu modelo
                        detail.getTeacher() != null ? detail.getTeacher().getFirstName() : null
                ))
                .toList();

        // 4. Retornar el DTO final ensamblado
        return new ReportCardResponseDTO(
                reportCard.getReportCardId(),
                studentDto,
                reportCard.getTerm() != null ? reportCard.getTerm().getTermDate() : null,
                detailsDto
        );
    }

}
