package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "report_card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_cart_id")
    private Long reportCardId;

    //FK del estudiante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private AcademicTermEntity term;

    @OneToMany(mappedBy = "reportCard", cascade = CascadeType.ALL)
    private List<ReportCardDetailEntity> reportCardDetails = new ArrayList<>();


}
