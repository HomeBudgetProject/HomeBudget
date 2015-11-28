package ua.com.homebudget.email;

import org.springframework.stereotype.Component;
import ua.com.homebudget.dto.EmailSendContainer;
import ua.com.homebudget.dto.templates.EmailTemplateCommon;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by kart on 15.11.2015.
 */
@Component
public interface EmailSender {
    //https://www.google.com/settings/security/lesssecureapps must be turn on
     String email = "homebudgetteam@gmail.com";
     String password = "ltymubdrjitkmrt";

    default Session getSession(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        return session;
    };

    void send(EmailSendContainer emailSendContainer,EmailTemplateCommon template);
}
