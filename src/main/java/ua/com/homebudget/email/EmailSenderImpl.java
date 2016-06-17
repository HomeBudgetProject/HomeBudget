package ua.com.homebudget.email;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ua.com.homebudget.dto.templates.EmailTemplateCommon;

/**
 * Created by kart on 15.11.2015.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    @Autowired 
    private TemplateEngine templateEngine;

    @Override
    public void send(final EmailTemplateCommon template, final Locale locale) {
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(template.getTo()));
            message.setSubject(template.getSubject());
            
            final Context ctx = new Context(locale);
            if (template.getContext() != null) {
                for (Entry<String, Object> entry: template.getContext().entrySet()) {
                    ctx.setVariable(entry.getKey(), entry.getValue());
                }
            }
            
            final String htmlContent = this.templateEngine.process(template.getTemplateName(), ctx);
            message.setText(htmlContent, "utf-8", "html");
            
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
