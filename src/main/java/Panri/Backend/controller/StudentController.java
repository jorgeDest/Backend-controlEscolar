package Panri.Backend.controller;

import Panri.Backend.DTOs.ReportCardResponseDTO;
import Panri.Backend.model.SubjectEntity;
import Panri.Backend.service.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ReportCardService reportCardService;

    @GetMapping("/my-califications/{reportId}")
    public ResponseEntity<ReportCardResponseDTO> getReportCard(@PathVariable Long reportId){

        ReportCardResponseDTO reportCardResponseDTO = reportCardService.getReportCardById(reportId);
        return ResponseEntity.ok(reportCardResponseDTO);

    }

    @GetMapping("/materiasCursando")
    public SubjectEntity getMateriasCursando(){
        return null;
    }


}
