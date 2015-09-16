package ua.com.homebudget.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource("classpath:system.properties")
public class SystemVersion {

    @Value("${version}")
    private String version;
}
