package Panri.Backend.repository;

import Panri.Backend.model.StudentGroupEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroupEntity, Long> {

    Optional<StudentGroupEntity> findByGradeLevelAndName(String gradeLevel, String name);



}
