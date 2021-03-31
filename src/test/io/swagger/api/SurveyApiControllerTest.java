package io.swagger.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.swagger.model.*;
import io.swagger.services.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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


    }


    @Test
    void createSurvey() throws Exception {

        //create a user
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //user login
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        //create Answeroptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));

        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        assertEquals(200,responseCreation.getStatus());

    }

    @Test
    void createSurveySubmissionCorrectly() throws Exception{

        //create a user
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //user login
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        //create Answeroptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));

        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        //parse data out of survey creation response
        responseBody =new JSONObject(responseCreation.getContentAsString());



        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);
        //Load full survey
        Survey survey=surveyService.findById(responseBody.getLong("id"));

        //set choices of submission due to clear existing answeroptions list and add one of the loaded survey as a choice
        answerOptionList.clear();
        answerOptionList.add(survey.getAnswerOptions().get(0));

        Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();



        assertEquals(responseCreation.getStatus(),200);


    }


    @Test
    void createSurveySubmissionNotCorrectly_submit2timesForOneSurvey() throws Exception{

        //create a user
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //user login
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        //create Answeroptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));

        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        //parse data out of survey creation response
        responseBody =new JSONObject(responseCreation.getContentAsString());



        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);
        //Load full survey
        Survey survey=surveyService.findById(responseBody.getLong("id"));

        //set choices of submission due to clear existing answeroptions list and add one of the loaded survey as a choice
        answerOptionList.clear();
        answerOptionList.add(survey.getAnswerOptions().get(0));

        Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();

        //parse cookie out of the response of the first submission and safe is in a local property
        JSONObject responseSubmission = new JSONObject(responseCreation.getContentAsString());
        JSONObject cookieParticipant = new JSONObject(responseSubmission.getString("participant"));
        String cookie= cookieParticipant.getString("Cookie");

        //setup of a new submission, but with the already stored participant -> cookie as the identifier -> find participant by cookie
        submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participantService.getByCookieID(cookie));
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();

        //parse response
        responseBody=new JSONObject(responseCreation.getContentAsString());

        //assert that the type is forbidden and the http response is 403 -> submission is not allowed two times
        assertEquals(responseCreation.getStatus(),403);
        assertEquals(responseBody.getString("type"),"Forbidden");


    }


    @Test
    void deleteSurveyById() {
        Survey survey =surveyService.addOrUpdateSurvey(new Survey(null, "aaa","bbb","nps","F7777","FE0000",null,null));


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
    void getSurveySubmissionsById() throws Exception {


        //create a user
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //user login
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        //create Answeroptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));


        String key = responseBody.getString("authKey");
        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        //parse data out of survey creation response
        responseBody =new JSONObject(responseCreation.getContentAsString());



        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);
        //Load full survey
        Survey survey=surveyService.findById(responseBody.getLong("id"));

        //set choices of submission due to clear existing answeroptions list and add one of the loaded survey as a choice
        answerOptionList.clear();
        answerOptionList.add(survey.getAnswerOptions().get(0));

        Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();


        //setup of a new submission
        participant.setIpAddress("168.178.192.3");
        submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();


        System.out.println(responseBody.getString("id"));
        responseCreation=mockMvc.perform(get("/survey/{id}/submissions",responseBody.getString("id")).header("api_key",key).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(survey))).andReturn().getResponse();
        System.out.println(responseCreation.getContentAsString());
        System.out.println(responseCreation.getStatus());

        assertEquals(responseCreation.getStatus(),200);


        ObjectReader objectReader = mapper.readerForUpdating(new ArrayList<Submission>());
        List<Submission> submissions = objectReader.readValue(responseCreation.getContentAsString());
        System.out.println(submissions.toString());


    }

    @Test
    void updateSurveyById() throws Exception {
        u1= new User("123@gmx.de","aaa","bbb","ccc");
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //user login
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        //create Answeroptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));

        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        //parse data out of survey creation response
        JSONObject responseBody2 =new JSONObject(responseCreation.getContentAsString());

        //update Object with parsed response
        ObjectReader objectReader = mapper.readerForUpdating(new Survey(null,null,null,null,null,null,null,null));
        Survey updatedSurvey = objectReader.readValue(responseCreation.getContentAsString());

        //change Survey properties for updating
        updatedSurvey.setName("hello2");
        updatedSurvey.setMode("nps");
        updatedSurvey.setAccentColor("#0000F");
        updatedSurvey.setBackgroundColor("#0011FE");
        updatedSurvey.setQuestionText("Hello2?");

        //update survey
        responseCreation=mockMvc.perform(post("/survey/{id}",updatedSurvey.getId()).header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(updatedSurvey))).andReturn().getResponse();

        //parsing the response
        System.out.println(responseCreation.getContentAsString());
        responseBody2 =new JSONObject(responseCreation.getContentAsString());

        //test the results -> compared with first survey object : all of the changes means that properties will be not equal compared to each other
        assertNotEquals(s1.getName(),responseBody2.getString("name"));
        assertNotEquals(s1.getQuestionText(),responseBody2.getString("questionText"));
        assertNotEquals(s1.getBackgroundColor(),responseBody2.getString("backgroundColor"));
        assertNotEquals(s1.getAccentColor(),responseBody2.getString("accentColor"));
    }
}