package collabhubbr.users.service.impl;

import collabhubbr.users.controller.DTO.RequestMailDTO;
import collabhubbr.users.controller.DTO.ResponseMailDTO;
import collabhubbr.users.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderFrom;

    @Override
    public ResponseMailDTO sendMail(RequestMailDTO requestMailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderFrom);
        mailMessage.setTo(requestMailDTO.toMail());
        mailMessage.setSubject("Verificação de Email");
        mailMessage.setText("Formatação contendo o token");
        mailSender.send(mailMessage);
        return new ResponseMailDTO("danieltenorio2046@gmail.com", "Daniel Tenorio", "Daniel Tenorio");
    }
}
