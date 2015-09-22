package ua.com.homebudget.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.homebudget.DblIntegrationTest;
import ua.com.homebudget.dto.sequences.user.GroupUser;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.User;
import ua.com.homebudget.repository.RoleRepository;
import ua.com.homebudget.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

@Transactional
@DatabaseSetup("user.xml")
public class UserServiceTest extends DblIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    static Validator validator;

    @BeforeClass
    public static void before() throws Exception {
        ValidatorFactory config = Validation.buildDefaultValidatorFactory();
        validator = config.getValidator();
    }

    Set<ConstraintViolation<UserRequest>> constraintViolations;

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

    @Test
    public void testRegisterSpaceEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail(" ");
        request.setPassword("123456");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The email must be between 6 and 60 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testRegisterSpacePassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword(" ");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The password must be between 6 and 100 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test()
    public void testRegisterWithSpaceEmail() throws Exception {
        Integer usersSize = userRepository.findAll().size();
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg ");
        request.setPassword("123456");
        userService.register(request);
        User user = userRepository.findByEmail("dfghj@sdfgh.dfg");
        Assert.assertEquals(usersSize + 1, userRepository.findAll().size());
        Assert.assertEquals("dfghj@sdfgh.dfg", user.getEmail());
        Assert.assertNotNull(user.getUserRole());
    }

    @Test()
    public void testRegisterWithSpacePassword() throws Exception {
        Integer usersSize = userRepository.findAll().size();
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword(" 123456");
        userService.register(request);
        User user = userRepository.findByEmail(request.getEmail());
        Assert.assertEquals(usersSize + 1, userRepository.findAll().size());
        Assert.assertEquals("123456", user.getPassword());
        Assert.assertNotNull(user.getUserRole());
    }

    @Test
    public void testRegisterEmptyEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("");
        request.setPassword("123456");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The email must be between 6 and 60 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testRegisterEmptyPassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword("");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The password must be between 6 and 100 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testRegisterLargeEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("123456789fghjhgfdfghjkl0123456789012345@6789012345678901234567890123456789.01234567890");
        request.setPassword("123456");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The email must be between 6 and 60 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testRegisterLargePassword() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghj@sdfgh.dfg");
        request.setPassword("1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789095");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "The password must be between 6 and 100 characters",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testRegisterNotEmail() throws Exception {
        UserRequest request = new UserRequest();
        request.setEmail("dfghjsdfgh.dfg");
        request.setPassword("123456");
        constraintViolations =
                validator.validate(request, GroupUser.class);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals(
                "Email is not valid",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(2);
        User user = userRepository.findOne(2);
        Assert.assertNull(user);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteUserNotFound() {
        userService.deleteUser(-1);
    }

}