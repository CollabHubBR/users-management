package collabhubbr.users.service;


import collabhubbr.users.controller.DTO.RequestMailDTO;

public interface MailService {
    void sendMail(RequestMailDTO requestMailDTO);
}
