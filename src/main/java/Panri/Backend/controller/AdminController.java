package Panri.Backend.controller;

import Panri.Backend.DTOs.RegisterStudentDTO;
import Panri.Backend.DTOs.RegisterTeacherDTO;
import Panri.Backend.DTOs.StudentResponseDTO;
import Panri.Backend.model.StudentEntity;
import Panri.Backend.model.TeacherEntity;
import Panri.Backend.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Controller encargado solamente de registrar alumnos y maestros
 * dentro del sistema.
 *
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired RegisterService registerService;

    @PostMapping("/registerStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO registerStudent(@RequestBody @Valid RegisterStudentDTO registerStudentDTO){

        StudentEntity student = registerService.registerStudent(registerStudentDTO);
        StudentResponseDTO responseDTO = new StudentResponseDTO();

        responseDTO.setFirstName(student.getFirstName());
        responseDTO.setLastName(student.getLastName());
        responseDTO.setEnrollmentCode(student.getEnrollmentCode());

        if(student.getStudentGroup() != null){
            responseDTO.setGrade(student.getStudentGroup().getGradeLevel());
            responseDTO.setGroupName(student.getStudentGroup().getName());
        }
        return responseDTO;

    }

    @PostMapping("/registerTeacher")
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherEntity registerTeacher(@RequestBody @Valid RegisterTeacherDTO teacherDTO){
        return registerService.registerTeacher(teacherDTO);

    }

}
