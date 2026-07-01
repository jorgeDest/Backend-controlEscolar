package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @Column(name = "teacher_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "employee_Number")
    private String employeeNumber;

    //FK User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id" , unique = true)
    private User user;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ReportCardDetail> reportCardDetails = new ArrayList<>();

}
