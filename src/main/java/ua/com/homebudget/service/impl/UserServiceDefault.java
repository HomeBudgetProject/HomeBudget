package ua.com.homebudget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.exception.UserServiceException;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.RoleRepository;
import ua.com.homebudget.repository.UserRepository;
import ua.com.homebudget.service.UserService;

import java.util.List;

@Component
public class UserServiceDefault implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        if(userRepository.findByEmail(email)==null)throw new UserServiceException("The email does not exist");
        return userRepository.findByEmail(email);
    }

    public void register(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            throw new UserServiceException("This email is already taken");
        }
        user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserRole(roleRepository.findByName("user"));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        try {
            getUser(email);
        }
        catch (UserServiceException ex){
            throw new UserServiceException("User not found",ex);
        }
        userRepository.delete(getUser(email).getUserId());
    }

}
