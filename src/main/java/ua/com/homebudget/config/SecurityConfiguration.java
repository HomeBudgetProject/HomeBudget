package ua.com.homebudget.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import ua.com.homebudget.security.RESTAuthenticationEntryPoint;
import ua.com.homebudget.security.RESTAuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String LOGIN_ENDPOINT = "/api/login";
    private final String LOGOUT_ENDPOINT = "/api/logout";
    private final String[] API_PUBLIC_ENDPOINTS = {
            LOGIN_ENDPOINT, "/api/users/register", "/api/users/resetPassword", 
            "/api/users/changePassword", "/api/users/whoami", "/api/info/*"};
    private final String API_MATCHER = "/api/**";
    private final String AUTHORIZATION_COOKIE_NAME = "auth_key";
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(API_PUBLIC_ENDPOINTS).permitAll()
            .antMatchers(API_MATCHER).authenticated();
        http.csrf().disable();
        http.formLogin().loginPage(LOGIN_ENDPOINT);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(new SimpleUrlAuthenticationFailureHandler());
        http.logout().logoutUrl(LOGOUT_ENDPOINT);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsService;
    }
    
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.getSessionCookieConfig().setName(AUTHORIZATION_COOKIE_NAME);
            }
        };

    }
}
