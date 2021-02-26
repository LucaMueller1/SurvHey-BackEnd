package io.swagger.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthManager implements AuthenticationManager {
    private static final Logger log = LoggerFactory.getLogger(ApiKeyAuthManager.class);

    private String principalRequestValue = "test123";

    public ApiKeyAuthManager() {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (!principal.equals(principalRequestValue)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            log.info("Successfully authenticated with key: " + principal);
            authentication.setAuthenticated(true);
            return authentication;
        }
    }


}