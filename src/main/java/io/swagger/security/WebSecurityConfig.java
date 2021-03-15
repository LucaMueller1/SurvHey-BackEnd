package io.swagger.security;

import io.swagger.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Value("${survhey.auth.api-key.name}")
    private String API_KEY_AUTH_HEADER_NAME;

    @Autowired
    private AuthService authService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyFilter filter = new ApiKeyFilter(API_KEY_AUTH_HEADER_NAME);
        filter.setAuthenticationManager(new ApiKeyAuthManager(authService));

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
                .antMatchers("/api-docs/**")
                .permitAll()
                .antMatchers("/swagger-ui/**")
                .permitAll()
                .antMatchers("/swagger-resources/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    }


}
