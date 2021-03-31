package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Survey;
import io.swagger.model.User;
import io.swagger.services.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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

    @Autowired
    private MockMvc mockMvc;

    User u1;

    @Before
    public void init () throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        //create a user
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        MockHttpServletResponse response=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

    }


    @Test
    void createSurvey() throws Exception {


        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,null);
        MockHttpServletResponse response=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());

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