package Panri.Backend.controller;

import Panri.Backend.DTOs.RegisterStudentDTO;
import Panri.Backend.DTOs.RegisterTeacherDTO;
import Panri.Backend.DTOs.StudentResponseDTO;
import Panri.Backend.model.Student;
import Panri.Backend.model.Teacher;
import Panri.Backend.model.User;
import Panri.Backend.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Controller encargado solamente de registrar alumnos y maestros
 * dentro del sistema.
 *
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired RegisterService registerService;

    @PostMapping("/registerUsers")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        //Primero deberíamos de validar que ese usuario no exista.
        //Después validar que el tipo de dato es correcto.
        User newUser = registerService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/registerStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO registerStudent(@RequestBody @Valid RegisterStudentDTO registerStudentDTO){

        Student student = registerService.registerStudent(registerStudentDTO);

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
    public Teacher registerTeacher(@RequestBody @Valid RegisterTeacherDTO teacherDTO){
        return registerService.registerTeacher(teacherDTO);

    }

}
