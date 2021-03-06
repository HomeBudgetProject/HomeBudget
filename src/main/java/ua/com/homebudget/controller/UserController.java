package ua.com.homebudget.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.com.homebudget.dto.ChangePasswordRequest;
import ua.com.homebudget.dto.ResetPasswordRequest;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.dto.sequences.user.GroupUser;
import ua.com.homebudget.exception.UserServiceException;
import ua.com.homebudget.model.User;
import ua.com.homebudget.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public User getUser() {
        return userService.getUser(userService.getCurrentUser());
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public String getPrincipal() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Validated({GroupUser.class}) @RequestBody UserRequest request, BindingResult bindingResults) {
        if (bindingResults.hasErrors()) {
            //Working only for one error
            for (FieldError error : bindingResults.getFieldErrors()) {
                throw new UserServiceException(error.getDefaultMessage());
            }
        } else {
            userService.register(request);
        }
    }

    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public void resetPassword(@RequestBody ResetPasswordRequest email, HttpServletRequest request){

        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String baseUrl = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";

        userService.sendPasswordRequestEmail(email.getEmail(), baseUrl);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public void changePassword(@RequestBody ChangePasswordRequest request){
        userService.changePassword(request);
    }
}
