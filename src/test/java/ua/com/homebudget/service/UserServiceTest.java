package ua.com.homebudget.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.homebudget.DblIntegrationTest;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.UserRepository;

@DatabaseSetup("user.xml")
public class UserServiceTest extends DblIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testRegister() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("pupkin@milo.us");
        request.setPassword("qwerty");
        userService.register(request);
        User user = userRepository.findByEmail(request.getEmail());
        Assert.assertEquals(2, userRepository.findAll().size());
        Assert.assertEquals(request.getPassword(), user.getPassword());
    }

    @Test(expected = RuntimeException.class)
    public void testRegisterTakenEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("some@mail.com");
        request.setPassword("qwerty");
        userService.register(request);
    }
}