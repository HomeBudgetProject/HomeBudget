package ua.com.homebudget.dto.templates;

/**
 * Created by kart on 16.11.2015.
 */
public class ResetPasswordTemplate implements EmailTemplateCommon {
    private static final String subject="Reset password";
    private static final String message="Please, reset your password";

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
