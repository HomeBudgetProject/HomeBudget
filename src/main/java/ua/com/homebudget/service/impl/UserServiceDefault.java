package ua.com.homebudget.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.exception.UserServiceException;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.RoleRepository;
import ua.com.homebudget.repository.UserRepository;
import ua.com.homebudget.service.MessageService;
import ua.com.homebudget.service.UserService;

@Component
public class UserServiceDefault implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    MessageService messageSource;
    

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        
        if (user==null) {
            throw new UserServiceException(
                    messageSource.getMessage("err.email.not.exists"));
        }
        
        return user;
    }

    public void register(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            throw new UserServiceException(
                    messageSource.getMessage("err.user.already.exists"));
        }
        user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserRole(roleRepository.findByName("user"));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = null;
        try {
            user = getUser(email);
        }
        catch (UserServiceException ex){
            throw new UserServiceException(
                    messageSource.getMessage("err.user.not.found"), ex);
        }
        
        String currentUser = getCurrentUser();
        if (currentUser != null && currentUser.equals(email)) {
            userRepository.delete(user.getUserId());
            SecurityContextHolder.getContext().setAuthentication(null);
        } else {
            throw new UserServiceException(messageSource.getMessage("err.operation.not.allowed"));
        }

    }
    
    public String getCurrentUser() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
