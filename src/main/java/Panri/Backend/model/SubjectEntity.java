package Panri.Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjectEntity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_code")
    private String subjectCode;

    @OneToMany(mappedBy = "subjectEntity", cascade = CascadeType.ALL)
    private List<ReportCardDetailEntity> reportCardDetails = new ArrayList<>();

}
