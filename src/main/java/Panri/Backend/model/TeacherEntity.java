package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TeacherEntity {

    @Id
    @Column(name = "teacher_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "employee_Number", unique = true)
    private String employeeNumber;

    //FK User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id" , unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ReportCardDetailEntity> reportCardDetails = new ArrayList<>();

}
