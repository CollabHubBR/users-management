package collabhubbr.users.service;


import collabhubbr.users.controller.DTO.RequestMailDTO;
import collabhubbr.users.controller.DTO.ResponseMailDTO;

public interface MailService {
    public ResponseMailDTO sendMail(RequestMailDTO requestMailDTO);
}
