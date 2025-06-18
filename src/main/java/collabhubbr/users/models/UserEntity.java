package collabhubbr.users.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<RoleEntity> roles;
}
