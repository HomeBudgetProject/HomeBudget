package ua.com.homebudget.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import ua.com.homebudget.DblIntegrationTest;
 
@DatabaseSetup("../service/user.xml")
public class UserControllerTest extends DblIntegrationTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Ignore
    public void testSendpasswordRecoveryEmail() throws Exception {
        String requestData = "{\"email\": \"some@mail.com\"}";
        mockMvc.perform(post("/api/users/resetPassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestData))
                .andExpect(status().isOk());
    }

}
