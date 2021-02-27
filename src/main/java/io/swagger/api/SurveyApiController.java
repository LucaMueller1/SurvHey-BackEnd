package io.swagger.api;

import io.swagger.model.Analysis;
import io.swagger.model.ApiError;
import io.swagger.model.Results;
import io.swagger.model.Submission;
import io.swagger.model.Survey;
import io.swagger.model.SurveyPrepare;
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
public class SurveyApiController implements SurveyApi {

    private static final Logger log = LoggerFactory.getLogger(SurveyApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SurveyApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Survey> createSurvey(@Parameter(in = ParameterIn.DEFAULT, description = "Created survey object", schema=@Schema()) @Valid @RequestBody SurveyPrepare body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Survey>(objectMapper.readValue("{\n  \"name\" : \"name\",\n  \"questions\" : [ {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  }, {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  } ],\n  \"id\" : 0,\n  \"user\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 0,\n    \"email\" : \"email\"\n  }\n}", Survey.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Survey>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Survey>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Submission> createSurveySubmission(@Parameter(in = ParameterIn.PATH, description = "ID of survey to create a new submission for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Submission>(objectMapper.readValue("{\n  \"survey_id\" : 1,\n  \"answers\" : [ {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  }, {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  } ],\n  \"id\" : 0,\n  \"participant\" : {\n    \"id\" : 6,\n    \"ip_address\" : \"ip_address\"\n  },\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}", Submission.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Submission>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Submission>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to update", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Analysis> getSurveyAnalysisById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return an analysis for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Analysis>(objectMapper.readValue("{\n  \"survey_id\" : 6,\n  \"amount\" : 527,\n  \"id\" : 0,\n  \"countries\" : {\n    \"Germany\" : 69,\n    \"USA\" : 420,\n    \"Spain\" : 11,\n    \"Norway\" : 27\n  }\n}", Analysis.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Analysis>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Analysis>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Survey> getSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Survey>(objectMapper.readValue("{\n  \"name\" : \"name\",\n  \"questions\" : [ {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  }, {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  } ],\n  \"id\" : 0,\n  \"user\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 0,\n    \"email\" : \"email\"\n  }\n}", Survey.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Survey>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Survey>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Results>> getSurveyResultsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return results for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Results>>(objectMapper.readValue("[ {\n  \"mode\" : \"radio\",\n  \"survey_id\" : 0,\n  \"choices\" : [ \"\", \"\" ]\n}, {\n  \"mode\" : \"radio\",\n  \"survey_id\" : 0,\n  \"choices\" : [ \"\", \"\" ]\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Results>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Results>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Submission>> getSurveySubmissionsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return all submissions for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Submission>>(objectMapper.readValue("[ {\n  \"survey_id\" : 1,\n  \"answers\" : [ {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  }, {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  } ],\n  \"id\" : 0,\n  \"participant\" : {\n    \"id\" : 6,\n    \"ip_address\" : \"ip_address\"\n  },\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}, {\n  \"survey_id\" : 1,\n  \"answers\" : [ {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  }, {\n    \"id\" : 5,\n    \"choices\" : [ \"\", \"\" ],\n    \"question_id\" : 5\n  } ],\n  \"id\" : 0,\n  \"participant\" : {\n    \"id\" : 6,\n    \"ip_address\" : \"ip_address\"\n  },\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Submission>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Submission>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Survey> updateSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to update", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Survey>(objectMapper.readValue("{\n  \"name\" : \"name\",\n  \"questions\" : [ {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  }, {\n    \"mode\" : \"radio\",\n    \"survey_id\" : 1,\n    \"id\" : 6,\n    \"text\" : \"text\",\n    \"answerOptions\" : [ {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    }, {\n      \"id\" : 5,\n      \"question_id\" : 5,\n      \"content\" : \"\"\n    } ]\n  } ],\n  \"id\" : 0,\n  \"user\" : {\n    \"firstName\" : \"firstName\",\n    \"lastName\" : \"lastName\",\n    \"password\" : \"password\",\n    \"id\" : 0,\n    \"email\" : \"email\"\n  }\n}", Survey.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Survey>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Survey>(HttpStatus.NOT_IMPLEMENTED);
    }

}
