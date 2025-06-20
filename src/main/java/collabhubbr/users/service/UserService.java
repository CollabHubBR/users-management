package collabhubbr.users.service;

import collabhubbr.users.DTO.RequestLoginDTO;
import collabhubbr.users.DTO.RequestUserDTO;
import collabhubbr.users.DTO.ResponseLoginDTO;
import collabhubbr.users.DTO.ResponseNewUserDTO;

public interface UserService {
    ResponseNewUserDTO createAccount(RequestUserDTO user);
    ResponseLoginDTO loginAccount(RequestLoginDTO user);
}
