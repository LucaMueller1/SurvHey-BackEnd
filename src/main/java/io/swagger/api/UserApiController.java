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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

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
        //userService.deleteUser();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> getUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"firstName\" : \"Luca\",\n  \"lastName\" : \"Mueller\",\n  \"password\" : \"lol123\",\n  \"email\" : \"\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
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
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
