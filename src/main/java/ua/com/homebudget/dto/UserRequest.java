package ua.com.homebudget.dto;

import lombok.Data;
import ua.com.homebudget.dto.sequences.user.GroupUser;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class UserRequest {

    @Size(min = 6, max = 60, message = "The email must be between 6 and 60 characters", groups = GroupUser.EmailStepOne.class)
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$", message = "Email is not valid", groups = GroupUser.EmailStepTwo.class)
    private String email;

    @Size(min = 6, max = 100, message = "The password must be between 6 and 100 characters", groups = GroupUser.PasswordStepOne.class)
    private String password;

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
