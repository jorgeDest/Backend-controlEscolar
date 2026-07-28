package Panri.Backend.repository;

import Panri.Backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    boolean existsByEnrollmentCode(String enrollmentCode);

}
