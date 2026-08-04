package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//anotaciones
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentEntity {
    //atributos correspondientes al modelo
    @Id
    @Column(name = "student_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    //Matrícula del estudiante
    @Column(name = "enrollment_code", unique = true)
    private String enrollmentCode;

    //FK Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id" , unique = true)
    private UserEntity user;

    //FK Grupo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudentGroupEntity studentGroup;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ReportCardEntity> reportCards = new ArrayList<>();

}
