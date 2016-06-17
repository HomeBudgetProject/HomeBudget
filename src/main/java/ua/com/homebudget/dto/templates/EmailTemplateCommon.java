package ua.com.homebudget.dto.templates;

import java.util.HashMap;

import lombok.Data;

/**
 * Created by kart on 16.11.2015.
 */
@Data
public class EmailTemplateCommon {
    private String subject;
    private String templateName;
    private String to;
    
    private HashMap<String, Object> context; 
    
    public void setParameter(String name, Object value) {
        if (context == null) {
            context = new HashMap<>();
        }
        
        context.put(name, value);
    }
    
}
