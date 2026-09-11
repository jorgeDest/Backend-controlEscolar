package Panri.Backend.controller;


import Panri.Backend.DTOs.ReportCardDetailDTO;
import Panri.Backend.DTOs.ResponseCardDetailDTO;
import Panri.Backend.model.ReportCardDetailEntity;
import Panri.Backend.service.ReportCardDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ReportCardDetailService reportCardDetailService;

    //Emitir el reporCardDetail
    @PostMapping("/reportCardDetail")
    public ResponseEntity<ResponseCardDetailDTO> generateReportCardDetail(@RequestBody @Valid ReportCardDetailDTO cardDetailDTO) {

        ReportCardDetailEntity reportCardDetailEntity = reportCardDetailService.generateCardDetail(cardDetailDTO);

        ResponseCardDetailDTO responseCardDetailDTO = new ResponseCardDetailDTO();

        responseCardDetailDTO.setScore(reportCardDetailEntity.getScore());
        responseCardDetailDTO.setSubjectName(reportCardDetailEntity.getSubjectEntity().getName());
        responseCardDetailDTO.setSubjectCode(reportCardDetailEntity.getSubjectEntity().getSubjectCode());
        responseCardDetailDTO.setGrade(reportCardDetailEntity.getReportCard().getStudent().getStudentGroup().getName());
        responseCardDetailDTO.setLevel(reportCardDetailEntity.getReportCard().getStudent().getStudentGroup().getGradeLevel());
        responseCardDetailDTO.setTermName(reportCardDetailEntity.getReportCard().getTerm().getTermDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCardDetailDTO);

    }

}
