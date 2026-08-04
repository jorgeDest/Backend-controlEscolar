package Panri.Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academic_terms")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AcademicTermEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long AcademicTermId;

    @Column(name = "term_date")
    private LocalDate termDate;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<ReportCardEntity> reportCard = new ArrayList<>();
}
