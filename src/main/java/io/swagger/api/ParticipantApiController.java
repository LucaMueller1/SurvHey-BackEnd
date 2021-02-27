package io.swagger.api;

import io.swagger.model.ApiError;
import io.swagger.model.Survey;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T14:36:57.683Z[GMT]")
@RestController
public class ParticipantApiController implements ParticipantApi {

    private static final Logger log = LoggerFactory.getLogger(ParticipantApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ParticipantApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Survey>> getParticipantsSurveys(@Parameter(in = ParameterIn.PATH, description = "id of participant to fetch surveys from", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Survey>>(objectMapper.readValue("[ {\n  \"name\" : \"name\",\n  \"questions\" : [ {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  }, {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  } ],\n  \"id\" : 0,\n  \"user\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 0,\n    \"email\" : \"email\"\n  }\n}, {\n  \"name\" : \"name\",\n  \"questions\" : [ {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  }, {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  } ],\n  \"id\" : 0,\n  \"user\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 0,\n    \"email\" : \"email\"\n  }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Survey>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Survey>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
