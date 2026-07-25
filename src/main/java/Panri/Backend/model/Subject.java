package Panri.Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_code")
    private String subjectCode;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<ReportCardDetail> reportCardDetails = new ArrayList<>();

}
