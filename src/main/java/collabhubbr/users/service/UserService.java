package collabhubbr.users.service;

import collabhubbr.users.controller.DTO.*;

public interface UserService {
    ResponseNewUserDTO createAccount(RequestUserDTO user);
    ResponseLoginDTO loginAccount(RequestLoginDTO user);
    void updateAccount(String authorization, RequestUserDTO user);
    ResponseUserDTO getInfo(Long id);
}
