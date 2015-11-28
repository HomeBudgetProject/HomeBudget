package ua.com.homebudget.service;

import ua.com.homebudget.dto.EmailSendContainer;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUser(String email);
    void register(UserRequest request);
    void deleteUser(String email);
    void deleteUser(int id);
    String getCurrentUser();
    void resetPassword(EmailSendContainer emailSendContainer);

}
