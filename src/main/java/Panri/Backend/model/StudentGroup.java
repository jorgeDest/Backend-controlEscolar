package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_group")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_group_id")
    private Long StudentGroupId;

    @Column(name = "name")
    private String name;

    @Column(name = "grade_level")
    private Integer gradeLevel;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

}
