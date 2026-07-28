package Panri.Backend.repository;

import Panri.Backend.model.StudentGroup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    Optional<StudentGroup> findByGradeLevelAndName(Integer gradeLevel, String name);



}
