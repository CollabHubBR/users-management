package collabhubbr.users.service.impl;

import collabhubbr.users.controller.DTO.RequestMailDTO;
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
    public void sendMail(RequestMailDTO requestMailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderFrom);
        mailMessage.setTo(requestMailDTO.to());
        mailMessage.setSubject(requestMailDTO.subject());
        mailMessage.setText(requestMailDTO.text());
        mailSender.send(mailMessage);
    }
}
