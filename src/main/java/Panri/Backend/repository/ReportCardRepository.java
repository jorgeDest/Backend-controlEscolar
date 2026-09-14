package Panri.Backend.repository;

import Panri.Backend.model.ReportCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCardEntity, Long> {
    //hacks gemini
    @Query("SELECT DISTINCT rc FROM ReportCardEntity rc " +
            "JOIN FETCH rc.student s " +
            "LEFT JOIN FETCH s.studentGroup sg " +
            "LEFT JOIN FETCH rc.term t " +
            "LEFT JOIN FETCH rc.reportCardDetails rcd " +
            "LEFT JOIN FETCH rcd.subjectEntity sub " +
            "LEFT JOIN FETCH rcd.teacher tch " +
            "WHERE rc.reportCardId = :reportCardId")
    Optional<ReportCardEntity> findByReportCardId(@Param("reportCardId") Long reportCardId);

}
