package Panri.Backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "report_detail")
@NoArgsConstructor
@Getter
@Setter
public class ReportCardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportCardDetail;

    @Column(name = "score")
    private double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_card_id")
    private ReportCard reportCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
