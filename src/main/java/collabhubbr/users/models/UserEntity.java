package collabhubbr.users.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    private Long id;

    private String username;
    private String email;
    private String password;

    private String roles;
}
