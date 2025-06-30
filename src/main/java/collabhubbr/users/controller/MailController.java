package collabhubbr.users.controller;


import collabhubbr.users.controller.DTO.RequestMailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/mail")
public interface MailController{
    @PostMapping("/confirm/{id}")
    ResponseEntity<Void> sendTokenMail(@RequestBody RequestMailDTO requestMailDTO);
}
