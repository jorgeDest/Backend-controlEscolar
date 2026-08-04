package Panri.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //FK rol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private RoleEntity role;

    // mappedBy indica que la entidad StudentEntity es la dueña de la relación.
    //por lo cual el valor "user" debe coincidir exactamente con el nombre de la clase StudentEntity.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentEntity student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TeacherEntity teacher;

}
