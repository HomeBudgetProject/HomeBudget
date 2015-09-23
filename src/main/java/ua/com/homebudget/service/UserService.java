package ua.com.homebudget.service;

import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public User getUser(String email);
    public void register(UserRequest request);
    public void deleteUser(String email);

}
