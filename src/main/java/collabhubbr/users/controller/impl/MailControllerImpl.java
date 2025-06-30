package collabhubbr.users.controller.impl;

import collabhubbr.users.controller.DTO.RequestMailDTO;
import collabhubbr.users.controller.MailController;
import collabhubbr.users.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {
    private final MailService mailService;

    @Override
    public ResponseEntity<Void> sendTokenMail(RequestMailDTO requestMailDT){
        mailService.sendMail(requestMailDT);
        return ResponseEntity.ok().build();
    }
}
