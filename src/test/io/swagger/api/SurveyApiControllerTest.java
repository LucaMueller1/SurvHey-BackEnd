package io.swagger.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.AnswerOption;
import io.swagger.model.Submission;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(response.getStatus(),200);

    }

    @Test
    void createSurveySubmission() throws Exception {

        //set answeroptions
        List<AnswerOption> answerOptions= new ArrayList<>();
        AnswerOption answerOption1 = new AnswerOption(null,null,"answer1");
        answerOptions.add(answerOption1);
        AnswerOption answerOption2 = new AnswerOption(null,null,"answer2");
        answerOptions.add(answerOption2);
        AnswerOption answerOption3 = new AnswerOption(null,null,"answer3");
        answerOptions.add(answerOption3);

        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,answerOptions);
        MockHttpServletResponse response=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        submissionService.addOrUpdateSubmission(new Submission(null, s1.getId(), OffsetDateTime.now(), answerOptions, answerOption1, ???));

        //look if works
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());
        assertEquals(response.getStatus(),200);
    }

    @Test
    void deleteSurveyById() throws Exception{
        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,null);
        MockHttpServletResponse response=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());

        //delete survey
        MockHttpServletResponse response2=mockMvc.perform(MockMvcRequestBuilders.delete("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());
        assertEquals(response.getStatus(),202);
    }

    @Test
    void getSurveyAnalysisById() throws Exception{

        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,null);
        MockHttpServletResponse response=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

    }

    @Test
    void getSurveyById() {
        //same as testing createSurvey
    }

    @Test
    void getSurveyResultsById() {
        //part of testing createSurvey
    }

    @Test
    void getSurveySubmissionsById() {
        //part of testing createSurvey
    }

    @Test
    void updateSurveyById() throws Exception {
        //set answeroptions
        List<AnswerOption> answerOptions= new ArrayList<>();
        AnswerOption answerOption1 = new AnswerOption(null,null,"answer1");
        answerOptions.add(answerOption1);
        AnswerOption answerOption2 = new AnswerOption(null,null,"answer2");
        answerOptions.add(answerOption2);
        AnswerOption answerOption3 = new AnswerOption(null,null,"answer3");
        answerOptions.add(answerOption3);

        Survey s1=new Survey(null,"Hallo","Hallo?","nps",null,null,u1,answerOptions);
        MockHttpServletResponse response=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());

        surveyService.addOrUpdateSurvey(s1, "different","Hallo?","nps",null,null,u1,answerOptions);
        MockHttpServletResponse response2=mockMvc.perform(post("/survey").header("'api_key':" ).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());

        //assertEquals(response.getContentAsString(), );
    }
}