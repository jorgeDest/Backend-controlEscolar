package Panri.Backend.repository;

import Panri.Backend.model.ReportCardDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCardDetailRepository extends JpaRepository<ReportCardDetailEntity, Long> {
}
