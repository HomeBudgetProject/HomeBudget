package ua.com.homebudget.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.com.homebudget.IntegrationTest;

public class MessageServiceTest extends IntegrationTest {

    @Autowired 
    MessageService messageService;
    
    @Test
    public void testMessageBundleAccessability() {
        assertNotNull("MessageService has not been configured", messageService);
        assertEquals("Operation is not allowed", 
                messageService.getMessage("err.operation.not.allowed"));;
    }

}
