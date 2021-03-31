package io.swagger.api;

import io.swagger.model.ApiError;
import io.swagger.model.AuthKey;
import io.swagger.model.User;
import io.swagger.model.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.services.AuthService;
import io.swagger.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")
@RestController
@CrossOrigin
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${survhey.auth.api-key.name}")
    private String API_KEY_AUTH_HEADER_NAME;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "User object to create", required=true, schema=@Schema()) @Valid @RequestBody User body) {

        String pwd = body.getPassword();
        String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());
        body.setPassword(hashed);
        userService.createUser(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser() {
        User user = authService.getUserByKey(request.getHeader(API_KEY_AUTH_HEADER_NAME));
        userService.deleteUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> getUser() {
        User user = authService.getUserByKey(request.getHeader(API_KEY_AUTH_HEADER_NAME));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    public ResponseEntity<AuthKey> loginUser(@Parameter(in = ParameterIn.DEFAULT, description = "Object that stores user login data", required=true, schema=@Schema()) @Valid @RequestBody UserLogin body) {
        //get body and check if credentials are valid
        AuthKey authKey = authService.login(body);
        if(authKey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), "Given credentials are incorrect"), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<AuthKey>(authKey, HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutUser() {
        AuthKey key = authService.getAuthKeyById(request.getHeader(API_KEY_AUTH_HEADER_NAME));
        authService.deleteAuthKey(key);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
