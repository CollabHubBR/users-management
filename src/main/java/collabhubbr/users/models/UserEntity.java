package collabhubbr.users.models;

import collabhubbr.users.controller.DTO.RequestUserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

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

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public UserEntity(RequestUserDTO requestUserDTO) {
        this.username = requestUserDTO.username();
        this.email = requestUserDTO.email();
        this.password = requestUserDTO.password();
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
}
