package com.skyline.skysmart.core.service.impls;

import com.skyline.skysmart.core.config.MailConfig;
import com.skyline.skysmart.core.service.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * [FEATURE INFO]<br/>
 * Mail service
 *
 * @author Skyline
 * @create 2022/6/16 15:29
 * @since 1.0.0
 */
@Service
public class MailService implements IMailService {

    private JavaMailSenderImpl mailSender;
    private MailConfig mailConfig;

    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setMailConfig(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    /**
     * send simple email
     *
     * @param receiver String, receiver's email
     * @param title    String, email title
     * @param content  String, email content
     */
    @Override
    public void sendSimpleMail(String receiver, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setFrom(mailConfig.getUsername());
        message.setSubject(title);
        message.setText(content);

        mailSender.send(message);
    }

}
