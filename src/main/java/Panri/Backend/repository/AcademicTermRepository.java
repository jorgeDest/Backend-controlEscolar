package Panri.Backend.repository;

import Panri.Backend.model.AcademicTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTermEntity, Long> {
}
