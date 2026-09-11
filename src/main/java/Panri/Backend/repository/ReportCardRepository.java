package Panri.Backend.repository;

import Panri.Backend.model.ReportCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCardEntity, Long> {
}
