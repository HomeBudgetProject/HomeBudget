package ua.com.homebudget.service;

import ua.com.homebudget.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public User getUser(Integer id);

}
