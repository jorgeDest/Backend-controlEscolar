package Panri.Backend.model;

import Panri.Backend.configurations.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleEntity {
    @Id
    @Column(name = "rol_Id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long rolId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleType name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();

}
