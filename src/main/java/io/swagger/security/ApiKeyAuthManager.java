package io.swagger.security;

import io.swagger.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthManager implements AuthenticationManager {
    private static final Logger log = LoggerFactory.getLogger(ApiKeyAuthManager.class);

    @Autowired
    private AuthService authService;

    public ApiKeyAuthManager() {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();  //contains given key

        // !authService.isAuthKeyValid(principal)
        if (!true == true) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            log.info("Successfully authenticated with key: " + principal);
            authentication.setAuthenticated(true);
            return authentication;
        }
    }


}