package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Survey;
import io.swagger.model.User;
import io.swagger.services.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class SurveyApiControllerTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private GeoLocationService geoLocationService;

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SurveyService surveyService;
    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }




    @Test
    void createSurvey() throws Exception {
        User u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/v2/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));


        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,null);
        mockMvc.perform(post("/v2/survey").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1)));

    }

    @Test
    void createSurveySubmission() {
    }

    @Test
    void deleteSurveyById() {
    }

    @Test
    void getSurveyAnalysisById() {
    }

    @Test
    void getSurveyById() {
    }

    @Test
    void getSurveyResultsById() {
    }

    @Test
    void getSurveySubmissionsById() {
    }

    @Test
    void updateSurveyById() {
    }
}