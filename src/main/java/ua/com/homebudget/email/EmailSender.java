package ua.com.homebudget.email;

import java.util.Locale;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.stereotype.Component;

import ua.com.homebudget.dto.templates.EmailTemplateCommon;

/**
 * Created by kart on 15.11.2015.
 */
@Component
public interface EmailSender {
    String email = "homebudget.bot@gmail.com";

    void send(final EmailTemplateCommon template, final Locale locale);
}
