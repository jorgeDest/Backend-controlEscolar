package Panri.Backend.service;

import Panri.Backend.DTOs.RegisterStudentDTO;
import Panri.Backend.DTOs.RegisterTeacherDTO;
import Panri.Backend.configurations.RoleType;
import Panri.Backend.model.Role;
import Panri.Backend.model.Student;
import Panri.Backend.model.Teacher;
import Panri.Backend.model.User;
import Panri.Backend.repository.RoleRepository;
import Panri.Backend.repository.StudentRepository;
import Panri.Backend.repository.TeacherRepository;
import Panri.Backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired private TeacherRepository teacherRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private StudentRepository studentRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private RoleRepository roleRepository;


    @Transactional
    public User registerUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("El usuario ya esta registrado");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);

    }

    // Recibimos 2 parámetros 1 el objeto de Student y un String para Guardar la contraseña
    @Transactional
    public Student registerStudent(RegisterStudentDTO registerStudentDTO){

        //Preguntar si existe el rol dentro del Enum RolType
        Role studentRole = roleRepository.findByName(RoleType.STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: el rol STUDENT no existe"));

        User newUser = new User();
        //asignamos el username mediante los datos de EnrollmentCode recibimos del JSON
        newUser.setUsername(registerStudentDTO.getEnrollmentCode());
        //hash a la contraseña para que no se guarde en texto plano
        newUser.setPassword(passwordEncoder.encode(registerStudentDTO.getPassword()));
        // Asignación de Rol
        newUser.setRole(studentRole);
        // Guardar el User
        User savedUser = userRepository.save(newUser);

        Student newStudent = new Student();
        newStudent.setFirstName(registerStudentDTO.getFirstName());
        newStudent.setLastName(registerStudentDTO.getLastName());
        newStudent.setEnrollmentCode(registerStudentDTO.getEnrollmentCode());

        // Asignar User al estudiante
        newStudent.setUser(savedUser);
        // Guardar el estudiante
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Teacher registerTeacher(RegisterTeacherDTO teacherDTO){

        Role teacherRol = roleRepository.findByName(RoleType.TEACHER)
                .orElseThrow(() -> new RuntimeException("Error: el rol TEACHER no existe"));

        User newUser = new User();

        newUser.setUsername(teacherDTO.getEmployeeNumber());
        newUser.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));
        newUser.setRole(teacherRol);
        User savedUser = userRepository.save(newUser);

        Teacher newTeacher = new Teacher();

        newTeacher.setFirstName(teacherDTO.getFirstName());
        newTeacher.setLastName(teacherDTO.getLastName());
        newTeacher.setEmployeeNumber(teacherDTO.getEmployeeNumber());

        newTeacher.setUser(savedUser);
        return teacherRepository.save(newTeacher);
    }


}
