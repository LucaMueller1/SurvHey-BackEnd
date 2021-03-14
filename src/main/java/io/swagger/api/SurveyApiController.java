package io.swagger.api;

import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.services.AuthService;
import io.swagger.services.SubmissionService;
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
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")
@RestController
@CrossOrigin
public class SurveyApiController implements SurveyApi {

    private static final Logger log = LoggerFactory.getLogger(SurveyApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Value("${survhey.auth.api-key.name}")
    private String API_KEY_AUTH_HEADER_NAME;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private AuthService authService;

    @org.springframework.beans.factory.annotation.Autowired
    public SurveyApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Survey> createSurvey(@Parameter(in = ParameterIn.DEFAULT, description = "Created survey object", schema=@Schema()) @Valid @RequestBody SurveyPrepare body) {
        User user = authService.getUserByKey(request.getHeader(API_KEY_AUTH_HEADER_NAME));

        Survey survey = surveyService.addOrUpdateSurvey(new Survey(null, body.getName(), body.getQuestionText(), body.getMode().name(), body.getBackgroundColor(), body.getAccentColor(), user, body.getAnswerOptions()));

        return new ResponseEntity<Survey>(survey, HttpStatus.OK);
    }

    public ResponseEntity<Submission> createSurveySubmission(@Parameter(in = ParameterIn.PATH, description = "ID of survey to create a new submission for", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "Created submission object for survey", schema=@Schema()) @Valid @RequestBody SubmissionPrepare body) {
        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        //check if user with ipAddress already participated in this survey
        if(submissionService.didAlreadyParticipate(survey, body.getIpAddress())) {
            return new ResponseEntity(new ApiError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), "You have already participated in this survey"), HttpStatus.FORBIDDEN);
        }

        //check if answerOptions exist
        if(!surveyService.validAnswerOptions(survey, body.getChoices())) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "AnswerOptions of survey not found"), HttpStatus.NOT_FOUND);
        }

        Submission submission = submissionService.addOrUpdateSubmission(new Submission(null, body.getIpAddress(), body.getSurveyId(), OffsetDateTime.now(), body.getChoices()));

        return new ResponseEntity<Submission>(submission, HttpStatus.OK);
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
        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        Analysis analysis = submissionService.getAnalysis(survey);

        if(analysis == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Analysis could not be loaded for given survey"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Analysis>(analysis, HttpStatus.OK);
    }

    public ResponseEntity<Survey> getSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Survey>(survey, HttpStatus.OK);
    }

    public ResponseEntity<SurveyResult> getSurveyResultsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return results for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        SurveyResult result = submissionService.getResults(survey);

        if(result == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Results could not be loaded for given survey"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SurveyResult>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<Submission>> getSurveySubmissionsById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to return all submissions for", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        List<Submission> submissions = submissionService.findAllBySurveyID(survey.getId());

        return new ResponseEntity<List<Submission>>(submissions, HttpStatus.OK);
    }

    public ResponseEntity<Survey> updateSurveyById(@Parameter(in = ParameterIn.PATH, description = "ID of survey to update", required=true, schema=@Schema()) @PathVariable("id") Long id,@Parameter(in = ParameterIn.DEFAULT, description = "Created survey object", schema=@Schema()) @Valid @RequestBody SurveyPrepare body) {
        User user = authService.getUserByKey(request.getHeader(API_KEY_AUTH_HEADER_NAME));

        Survey survey = surveyService.findById(id);

        if(survey == null) {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "Survey not found"), HttpStatus.NOT_FOUND);
        }

        survey.setName(body.getName());
        survey.setQuestionText(body.getQuestionText());
        survey.setMode(body.getMode().name());
        survey.getAnswerOptions().clear();
        survey.getAnswerOptions().addAll(body.getAnswerOptions());
        survey.setAccentColor(body.getAccentColor());
        survey.setBackgroundColor(body.getBackgroundColor());

        Survey rSurvey = surveyService.addOrUpdateSurvey(survey);


        return new ResponseEntity<Survey>(rSurvey, HttpStatus.OK);
    }

}
