package ua.com.homebudget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.UserRepository;
import ua.com.homebudget.service.UserService;

import java.util.List;

@Component
public class UserServiceDefault implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findOne(id);
    }

}
