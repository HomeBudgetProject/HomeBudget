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

    public User getUser(Integer id) {
        return userRepository.findOne(id);
    }

    public void register(UserRequest request) {
        request.setEmail(request.getEmail().trim());
        request.setPassword(request.getPassword().trim());
        if (request.getEmail().length() == 0) {
            throw new UserServiceException("Can't add empty email");
        }
        if (request.getPassword().length() == 0) {
            throw new UserServiceException("Can't add empty password");
        }
        if (request.getEmail().length() > 60) {
            throw new UserServiceException("Email is too large. Length is " + request.getEmail().trim().length() + ".  Max length is 60.");
        }
        if (request.getPassword().length() > 100) {
            throw new UserServiceException("Password is too large. Max length is 100.");
        }
        if (!request.getEmail().contains("@")) {
            throw new UserServiceException("Email is not have '@' letter");
        }
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
    public void deleteUser(Integer id) {
        if (getUser(id) == null) {
            throw new UserServiceException("User not found");
        }
        userRepository.delete(id);
    }

}
