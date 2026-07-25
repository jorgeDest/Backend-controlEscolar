package Panri.Backend.repository;

import Panri.Backend.configurations.RoleType;
import Panri.Backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role>findByName(RoleType name);
}
