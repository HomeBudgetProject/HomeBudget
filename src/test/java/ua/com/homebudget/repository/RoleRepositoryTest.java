package ua.com.homebudget.repository;import com.github.springtestdbunit.annotation.DatabaseSetup;import org.junit.Assert;import org.junit.Test;import org.springframework.beans.factory.annotation.Autowired;import ua.com.homebudget.DblIntegrationTest;import ua.com.homebudget.model.Role;import ua.com.homebudget.model.User;@DatabaseSetup("role.xml")public class RoleRepositoryTest extends DblIntegrationTest {    @Autowired    RoleRepository roleRepository;    @Test    public void testFindById() throws Exception {        Role role = roleRepository.findOne(1);        Assert.assertNotNull(role);    }    @Test    public void testFindByUnexistingId() throws Exception {        Role role = roleRepository.findOne(17484);        Assert.assertNull(role);    }    @Test    public void testFindByName() throws Exception {        Role role = roleRepository.findByName("admin");        Assert.assertNotNull(role);    }    @Test    public void testFindByUnexistingName() throws Exception {        Role role = roleRepository.findByName("dog");        Assert.assertNull(role);    }}