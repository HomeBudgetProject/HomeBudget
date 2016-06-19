package ua.com.homebudget.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import ua.com.homebudget.DblIntegrationTest;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.dto.sequences.user.GroupUser;
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

    private Set<ConstraintViolation<UserRequest>> constraintViolations;
    private static Validator validator;

    @BeforeClass
    public static void before() throws Exception {
        ValidatorFactory config = Validation.buildDefaultValidatorFactory();
        validator = config.getValidator();
    }
    
    @Before
    public void setUp() {
        mockSecurityContext();
    }

    @Test
    public void testGetUser() throws Exception {
        final String email = "weird/email@mail.com";
        User user = userService.getUser(email);
        assertNotNull(user);
        assertEquals(Integer.valueOf(3), user.getUserId());
        assertEquals(email, user.getEmail());
    }

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
        userService.deleteUser("some@mail.com");
        User user = userRepository.findByEmail("some@mail.com");
        assertNull(user);
    }

    @Test(expected = UserServiceException.class)
    public void testDeleteNonExistentUserById() {
        User user = userRepository.findOne(-1);
        assertNull(user);
        userService.deleteUser(-1);
    }

    @Test(expected = UserServiceException.class)
    public void testDeleteUserById() {
        User user = userRepository.findOne(2);
        assertNotNull(user);
        userService.deleteUser(2);
        user = userRepository.findOne(2);
        assertNull(user);
    }

    @Test(expected = UserServiceException.class)
    public void testDeleteAnotherUser() {
        // register a new user
        UserRequest request = new UserRequest();
        String email = "qwe@asd.zxc";
        request.setEmail(email);
        request.setPassword(" 123456");
        userService.register(request);
        User user = userRepository.findByEmail(request.getEmail());
        assertNotNull(user);
        assertThat(user.getEmail(), is(email));
        
        // try to delete a just created user being authenticated as 
        // another user
        userService.deleteUser(email);
    }

    @Test(expected = UserServiceException.class)
    public void testDeleteUserNotFound() {
        userService.deleteUser("-1");
    }

    @Test
    @Ignore
    public void testSendPasswordRequestEmail() {
        String email = "qwe@asd.zxc";
        userService.sendPasswordRequestEmail(email, "http://localhost:8080/");
    }

}