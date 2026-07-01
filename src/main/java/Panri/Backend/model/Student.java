package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//anotaciones
@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    //atributos correspondientes al modelo
    @Id
    @Column(name = "student_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    //Matricula del estudiante
    @Column(name = "enrollment_code")
    private String enrollmentCode;

    //FK Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id" , unique = true)
    private User user;

    //FK Grupo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudentGroup studentGroup;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ReportCard> reportCards = new ArrayList<>();




}
