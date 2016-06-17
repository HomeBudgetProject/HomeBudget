package ua.com.homebudget.service;

import java.util.List;

import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;

public interface UserService {

    List<User> getUsers();
    User getUser(String email);
    void register(UserRequest request);
    void deleteUser(String email);
    void deleteUser(int id);
    String getCurrentUser();
    void sendPasswordRequestEmail(String email);

}
