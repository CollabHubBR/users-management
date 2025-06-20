package collabhubbr.users.models;

import collabhubbr.users.controller.DTO.RequestUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private RoleName roles;

    public UserEntity(RequestUserDTO requestUserDTO) {
        this.username = requestUserDTO.username();
        this.email = requestUserDTO.email();
        this.password = requestUserDTO.password();
        this.roles = RoleName.PUBLIC_USER;
    }

    public void update(RequestUserDTO user) {
        if (user.username() != null) {
            this.username = user.username();
        }
        if (user.email() != null) {
            this.email = user.email();
        }
        if (user.password() != null) {
            this.password = user.password();
        }
    }

    @PrePersist
    protected void onCreate() {
        if (this.roles == null) {
            this.roles = RoleName.PUBLIC_USER;
        }
    }
}
