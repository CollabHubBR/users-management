package collabhubbr.users.service;

import collabhubbr.users.controller.DTO.RequestLoginDTO;
import collabhubbr.users.controller.DTO.RequestUserDTO;
import collabhubbr.users.controller.DTO.ResponseLoginDTO;
import collabhubbr.users.controller.DTO.ResponseNewUserDTO;

public interface UserService {
    ResponseNewUserDTO createAccount(RequestUserDTO user);
    ResponseLoginDTO loginAccount(RequestLoginDTO user);
}
