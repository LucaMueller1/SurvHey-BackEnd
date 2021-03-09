package io.swagger.api;

import io.swagger.model.ApiError;
import io.swagger.model.Survey;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.services.AuthService;
import io.swagger.services.SurveyService;
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
public class SurveysApiController implements SurveysApi {

    private static final Logger log = LoggerFactory.getLogger(SurveysApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${survhey.auth.api-key.name}")
    private String API_KEY_AUTH_HEADER_NAME;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @org.springframework.beans.factory.annotation.Autowired
    public SurveysApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Survey>> getSurveys() {
        User user = authService.getUserByKey(request.getHeader(API_KEY_AUTH_HEADER_NAME));

        List<Survey> surveys = surveyService.findByUser(user);

        return new ResponseEntity<List<Survey>>(surveys, HttpStatus.OK);
    }

}
