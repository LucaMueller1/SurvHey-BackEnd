package io.swagger.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    //@Value("${yourapp.http.api_key}")
    private static final String API_KEY_AUTH_HEADER_NAME = "api_key";

    private String principalRequestValue = "test123";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyFilter filter = new ApiKeyFilter(API_KEY_AUTH_HEADER_NAME);
        filter.setAuthenticationManager(new ApiKeyAuthManager());

        http.antMatcher("/**").
                csrf().
                disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .cors()
                .and()
                .headers().frameOptions().disable().
                and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/survey/{\\\\d+}")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/survey/{\\\\d+}/results")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/survey/{\\\\d+}/submission")
                .permitAll()
                .antMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated();

    }
}
