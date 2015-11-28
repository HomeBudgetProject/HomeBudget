package ua.com.homebudget.email;

import org.springframework.stereotype.Component;
import ua.com.homebudget.dto.EmailSendContainer;
import ua.com.homebudget.dto.templates.EmailTemplateCommon;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by kart on 15.11.2015.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    @Override
    public void send(EmailSendContainer emailSendContainer, EmailTemplateCommon template) {
        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailSendContainer.getEmail()));
            message.setSubject(template.getSubject());
            message.setText(template.getMessage());
            Transport.send(message);
        } catch (MessagingException e) {

        }
    }
}
