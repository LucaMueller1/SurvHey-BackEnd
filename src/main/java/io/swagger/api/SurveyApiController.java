package io.swagger.api;

import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class SurveyApiController implements SurveyApi {

    private static final Logger log = LoggerFactory.getLogger(SurveyApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public SurveyApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Survey> createSurvey(@Parameter(in = ParameterIn.DEFAULT, description = "Created survey object", schema=@Schema()) @Valid @RequestBody SurveyPrepare body) {

        User user = userService.findByEmail("Ka@rotte.de");

        if(user == null) {
            System.err.println("USER IS NULL");
        }

        Survey survey = surveyService.addOrUpdateSurvey(new Survey(null, body.getName(), body.getQuestionText(), body.getMode().name(), user, body.getAnswerOptions()));

        return new ResponseEntity<Survey>(survey, HttpStatus.OK);
    }

    public ResponseEntity<Submission> createSurveySubmission(@Parameter(in = ParameterIn.PATH, description = "ID of survey to create a new submission for", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "Created submission object for survey", schema=@Schema()) @Valid @RequestBody SubmissionPrepare body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Submission>(objectMapper.readValue("{\n  \"surveyId\" : 6,\n  \"ipAddress\" : \"ipAddress\",\n  \"id\" : 0,\n  \"choices\" : [ {\n    \"surveyId\" : 1,\n    \"id\" : 6,\n    \"content\" : \"Tesla\"\n  }, {\n    \"surveyId\" : 1,\n    \"id\" : 6,\n    \"content\" : \"Tesla\"\n  } ],\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}", Submission.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Submission>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Submission>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to update", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        Survey survey = surveyService.findById(id);
        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }
        surveyService.deleteSurvey(survey);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Analysis> getSurveyAnalysisById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return an analysis for", required=true, schema=@Schema()) @PathVariable("id") Long id) {

        return new ResponseEntity<Analysis>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Survey> getSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        User user = userService.findByEmail("Gur@ke.com");

        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Survey>(survey, HttpStatus.OK);
    }

    public ResponseEntity<SurveyResult> getSurveyResultsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return results for", required=true, schema=@Schema()) @PathVariable("id") Long id) {

        return new ResponseEntity<SurveyResult>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Submission>> getSurveySubmissionsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return all submissions for", required=true, schema=@Schema()) @PathVariable("id") Long id) {

        return new ResponseEntity<List<Submission>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Survey> updateSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to update", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "Created survey object", schema=@Schema()) @Valid @RequestBody SurveyPrepare body) {
        User user = userService.findByEmail("Gur@ke.com");

        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        survey.setName(body.getName());
        survey.setQuestionText(body.getQuestionText());
        survey.setMode(body.getMode().name());
        survey.getAnswerOptions().clear();
        survey.getAnswerOptions().addAll(body.getAnswerOptions());

        surveyService.addOrUpdateSurvey(survey);


        return new ResponseEntity<Survey>(HttpStatus.OK);
    }

}
