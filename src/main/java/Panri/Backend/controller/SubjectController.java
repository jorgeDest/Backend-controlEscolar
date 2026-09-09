package Panri.Backend.controller;


import Panri.Backend.DTOs.SubjectDTO;
import Panri.Backend.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Admin/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/newSubject")
    public ResponseEntity<String> newSubject(@RequestBody @Valid SubjectDTO subjectDTO){

        subjectService.createNewSubject(subjectDTO);

        return ResponseEntity.ok("Materia agregada correctamente");
    }



}
