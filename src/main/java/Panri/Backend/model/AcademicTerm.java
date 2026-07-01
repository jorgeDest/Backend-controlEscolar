package Panri.Backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academic_terms")
@Getter
@Setter
@NoArgsConstructor

public class AcademicTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long AcademicTermId;

    @Column(name = "term_date")
    private LocalDate termDate;

    @OneToMany(mappedBy = "term",cascade = CascadeType.ALL)
    private List<ReportCard> reportCard = new ArrayList<>();
}
