package ua.com.homebudget.service;

import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public User getUser(Integer id);
    public void register(UserRequest request);
    public void deleteUser(Integer id);

}
