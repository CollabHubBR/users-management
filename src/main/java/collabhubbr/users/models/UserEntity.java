package collabhubbr.users.models;

import jakarta.persistence.*;
import lombok.*;

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
    private RoleName roles;

    @PrePersist
    protected void onCreate() {
        if (this.roles == null) {
            this.roles = RoleName.PUBLIC_USER;
        }
    }
}
