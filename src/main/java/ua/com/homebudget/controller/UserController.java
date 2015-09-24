package ua.com.homebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.dto.sequences.user.GroupUser;
import ua.com.homebudget.model.User;
import ua.com.homebudget.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Validated({GroupUser.class}) @RequestBody UserRequest request) {
        userService.register(request);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }
}
