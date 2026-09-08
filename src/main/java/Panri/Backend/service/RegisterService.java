package Panri.Backend.service;

import Panri.Backend.DTOs.RegisterStudentDTO;
import Panri.Backend.DTOs.RegisterTeacherDTO;
import Panri.Backend.configurations.RoleType;
import Panri.Backend.model.*;
import Panri.Backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterService {

    @Autowired private TeacherRepository teacherRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private StudentRepository studentRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private RoleRepository roleRepository;

    @Autowired private StudentGroupRepository groupRepository;



    @Transactional
    public UserEntity registerUser(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("El usuario ya esta registrado");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);

    }

    // Recibimos 2 parámetros 1 el objeto de StudentEntity y un String para Guardar la contraseña
    @Transactional
    public StudentEntity registerStudent(RegisterStudentDTO registerStudentDTO){
        //¿Busca dentro del repo si existe un rol con esa característica?
        RoleEntity studentRole = roleRepository.findByName(RoleType.STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: El rol STUDENT no existe"));
        //preguntar si existe el grado y grupo
        StudentGroupEntity group = groupRepository.findByGradeLevelAndName(registerStudentDTO.getGrade(), registerStudentDTO.getGroupName())
                .orElseThrow(() -> new RuntimeException("Error: Ingresa un grado correcto para el estudiante"));

        String generateCode = generateUsername(
                registerStudentDTO.getFirstName(),
                registerStudentDTO.getLastName(),
                registerStudentDTO.getGrade(),
                registerStudentDTO.getGroupName()
        );

        String temporaryPassword = "123124";

        UserEntity newUser = new UserEntity();
        //asignamos el username mediante los datos de EnrollmentCode recibimos del JSON
        newUser.setUsername(generateCode);
        //hash a la contraseña para que no se guarde en texto plano
        newUser.setPassword(passwordEncoder.encode(temporaryPassword));
        // Asignación de Rol
        newUser.setRole(studentRole);
        // Guardar los datos en la entidad UserEntity
        UserEntity savedUser = userRepository.save(newUser);

        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstName(registerStudentDTO.getFirstName());
        newStudent.setLastName(registerStudentDTO.getLastName());
        newStudent.setEnrollmentCode(generateCode);
        newStudent.setStudentGroup(group);
        newStudent.setUser(savedUser);

        return studentRepository.save(newStudent);
    }

    @Transactional
    public TeacherEntity registerTeacher(RegisterTeacherDTO teacherDTO){

        RoleEntity teacherRol = roleRepository.findByName(RoleType.TEACHER)
                .orElseThrow(() -> new RuntimeException("Error: el rol TEACHER no existe"));

        if(teacherRepository.existsByEmployeeNumber(teacherDTO.getEmployeeNumber())){
            throw new IllegalArgumentException("Error: La matrícula " + teacherDTO.getEmployeeNumber() + " ya está registrada en el sistema.");
        }

        UserEntity newUser = new UserEntity();

        newUser.setUsername(teacherDTO.getEmployeeNumber());
        newUser.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));
        newUser.setRole(teacherRol);
        UserEntity savedUser = userRepository.save(newUser);

        TeacherEntity newTeacher = new TeacherEntity();

        newTeacher.setFirstName(teacherDTO.getFirstName());
        newTeacher.setLastName(teacherDTO.getLastName());
        newTeacher.setEmployeeNumber(teacherDTO.getEmployeeNumber());

        newTeacher.setUser(savedUser);
        return teacherRepository.save(newTeacher);
    }

    public String generateUsername(String firstName, String lastName, String grade, String group){
        //AÑO y obtención de los primeros datos
        String year = String.valueOf(LocalDate.now().getYear()).substring(2);
        String initial = getInitials(firstName, 2) + getInitials(lastName, 2);


        String code;
        int attempts = 0;
        final int MAX_ATTEMPTS = 10;

        long millis = System.currentTimeMillis();
        String millisStr = String.valueOf(millis);
        String millisPart = millisStr.substring(millisStr.length() - 4);


        do {
            code = year + initial + grade + group + millisPart;
            attempts++;
            if(attempts >= MAX_ATTEMPTS){
                throw new RuntimeException("No se pudo generar el usuario, intente de nuevo.");
            }
        }while(userRepository.findByUsername(code).isPresent());

        return code;
    }

    private String getInitials(String text, int length) {
        if (text == null || text.isBlank()) return "XX"; // fallback si viene vacío
        String clean = text.replaceAll("[^a-zA-Z]", "").toUpperCase();
        return clean.substring(0, Math.min(length, clean.length()));
    }

}
