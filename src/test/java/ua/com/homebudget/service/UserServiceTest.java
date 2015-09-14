package ua.com.homebudget.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.homebudget.DblIntegrationTest;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.exception.UserServiceException;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.RoleRepository;
import ua.com.homebudget.repository.UserRepository;

@Transactional
@DatabaseSetup("user.xml")
public class UserServiceTest extends DblIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testRegister() throws Exception {
        Integer usersSize = userRepository.findAll().size();
        UserRequest request = new UserRequest();
        request.setEmail("pupkin@milo.us");
        request.setPassword("qwerty");
        userService.register(request);
        User user = userRepository.findByEmail(request.getEmail());
        Assert.assertEquals(usersSize + 1, userRepository.findAll().size());
        Assert.assertEquals(request.getPassword(), user.getPassword());
        Assert.assertNotNull(user.getUserRole());
    }

    @Test(expected = RuntimeException.class)
    public void testRegisterTakenEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("some@mail.com");
        request.setPassword("qwerty");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterSpaceEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail(" ");
        request.setPassword("54");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterSpacePassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword(" ");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterEmptyEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("");
        request.setPassword("55");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterEmptyPassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword("");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterLargeEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("1234567890123456789012345678901234567890123456789012345678901234567890");
        request.setPassword("55");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterLargePassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword("1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789095");
        userService.register(request);
    }

    @Test(expected = UserServiceException.class)
    public void testRegisterNotEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghjsdfgh.dfg");
        request.setPassword("123");
        userService.register(request);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(2);
        User user = userRepository.findOne(2);
        Assert.assertNull(user);
    }

    @Test(expected = UserServiceException.class)
    public void testDeleteUserNotFound() {
        userService.deleteUser(-1);
    }

}