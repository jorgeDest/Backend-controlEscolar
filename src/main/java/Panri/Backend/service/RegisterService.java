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

        //Preguntar si existe el rol dentro del Enum RolType
        RoleEntity studentRole = roleRepository.findByName(RoleType.STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: El rol STUDENT no existe"));
        //debería preguntar si existe él grade y group
        StudentGroupEntity group = groupRepository.findByGradeLevelAndName(registerStudentDTO.getGrade(), registerStudentDTO.getGroupName())
                .orElseThrow(() -> new RuntimeException("Error: Ingresa un grado correcto para el estudiante"));
        if(studentRepository.existsByEnrollmentCode(registerStudentDTO.getEnrollmentCode())){
            throw new IllegalArgumentException("Error: La matrícula " + registerStudentDTO.getEnrollmentCode() + " ya está registrada en el sistema.");
        }

        UserEntity newUser = new UserEntity();
        //asignamos el username mediante los datos de EnrollmentCode recibimos del JSON
        newUser.setUsername(registerStudentDTO.getEnrollmentCode());
        //hash a la contraseña para que no se guarde en texto plano
        newUser.setPassword(passwordEncoder.encode(registerStudentDTO.getPassword()));
        // Asignación de Rol
        newUser.setRole(studentRole);
        // Guardar el User
        UserEntity savedUser = userRepository.save(newUser);

        StudentEntity newStudent = new StudentEntity();
        newStudent.setFirstName(registerStudentDTO.getFirstName());
        newStudent.setLastName(registerStudentDTO.getLastName());
        newStudent.setEnrollmentCode(registerStudentDTO.getEnrollmentCode());

        newStudent.setStudentGroup(group);

        // Asignar User al estudiante
        newStudent.setUser(savedUser);
        // Guardar el estudiante
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


}
