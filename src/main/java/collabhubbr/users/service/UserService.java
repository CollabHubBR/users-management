package collabhubbr.users.service;

import collabhubbr.users.models.UserEntity;
import collabhubbr.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

}
