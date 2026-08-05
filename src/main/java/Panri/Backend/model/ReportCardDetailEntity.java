package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "report_detail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportCardDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportCardDetail;

    @Column(name = "score")
    private double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_card_id")
    private ReportCardEntity reportCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

}
