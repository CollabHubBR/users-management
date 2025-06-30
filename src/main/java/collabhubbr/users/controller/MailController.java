package collabhubbr.users.controller;


import collabhubbr.users.controller.DTO.RequestMailDTO;
import collabhubbr.users.controller.DTO.ResponseMailDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/mail")
public interface MailController{
    @PostMapping ResponseMailDTO sendTokenMail(@RequestBody RequestMailDTO requestMailDTO);
}
