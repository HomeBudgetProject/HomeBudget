package ua.com.homebudget.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ua.com.homebudget.dto.templates.EmailTemplateCommon;
import ua.com.homebudget.dto.templates.ResetPasswordTemplate;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"ua.com.homebudget.repository"})
@EnableTransactionManagement
@ComponentScan("ua.com.homebudget")
public class AppConfiguration extends WebMvcAutoConfigurationAdapter  {

}
