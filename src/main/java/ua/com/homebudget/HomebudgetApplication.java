package ua.com.homebudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class HomebudgetApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomebudgetApplication.class, args);
    }
}
