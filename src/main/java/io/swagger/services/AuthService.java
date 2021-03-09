package io.swagger.services;

import io.swagger.model.AuthKey;
import io.swagger.model.User;
import io.swagger.model.UserLogin;
import io.swagger.repository.AuthKeyRepository;
import io.swagger.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthKeyRepository authRepository;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public AuthKey getAuthKeyById(String key) {
        Optional<AuthKey> authKey = authRepository.findById(key);
        if(authKey.isPresent()) {
            return authKey.get();
        } else {
            return null;
        }
    }

    public AuthKey login(UserLogin credentials) {
        User user = userRepository.findByEmail(credentials.getEmail());
        if(user == null) {
            return null;
        }

        if(BCrypt.checkpw(credentials.getPassword(), user.getPassword()))
        {
            AuthKey authKey = new AuthKey();
            authKey.setUser(user);
            authKey.setExpiry(OffsetDateTime.now().plusHours(12));
            authKey.setAuthKey("htregemgrhntmowgwrgomwe");
            return authRepository.save(authKey);
        }
        AuthKey authKey = new AuthKey();
        authKey.setUser(user);
        authKey.setExpiry(OffsetDateTime.now().plusHours(12));
        authKey.setAuthKey(generateNewToken());

        return authRepository.save(authKey);
    }

        return null;
    }

    public boolean isAuthKeyValid(String authKey) {
        Optional<AuthKey> key = authRepository.findById(authKey);
        if(!key.isPresent()) {
    public User getUserByKey(String key) {
        Optional<AuthKey> authKey = authRepository.findById(key);

        if(!authKey.isPresent()) {
            return null;
        } else {
            if(authKey.get().getExpiry().isBefore(OffsetDateTime.now())) {
                return null;
            } else {
                return authKey.get().getUser();
            }
        }
    }

    public boolean isAuthKeyValid(String key) {
        Optional<AuthKey> authKey = authRepository.findById(key);
        if(!authKey.isPresent()) {
            return false;
        } else {
            if(authKey.get().getExpiry().isBefore(OffsetDateTime.now())) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void deleteAuthKey(AuthKey authKey) {
        authRepository.delete(authKey);
    }

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
