package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.swagger.model.*;
import io.swagger.services.ParticipantService;
import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SurveyApiControllerTest {


    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    User u1;

    String authKey;


    @Before
    public void init (){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void createSurvey() throws Exception {
        //prepare user
        createUserAndLogin();

        //create survey
        Survey s1=createSurveyforTesting();
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        // take the response and parse it into a jsonObject
        JSONObject responseJson= new JSONObject(responseCreation.getContentAsString());

        //test response values

        assertEquals("Hallo",responseJson.getString("name"));
        assertEquals("Hallo?",responseJson.getString("questionText"));
        assertEquals("#FFFFFF",responseJson.getString("backgroundColor"));
        assertEquals("#EEEEEE",responseJson.getString("accentColor"));
        assertEquals(200,responseCreation.getStatus());


        //load survey from db
        s1=surveyService.findById(responseJson.getLong("id"));

        //test values from db
        assertEquals("Hallo",s1.getName());
        assertEquals("Hallo?",s1.getQuestionText());
        assertEquals("#FFFFFF",s1.getBackgroundColor());
        assertEquals("#EEEEEE",s1.getAccentColor());

        //test related answerOptions
        assertEquals(3,s1.getAnswerOptions().size());
        assertEquals("answer1",s1.getAnswerOptions().get(0).getContent());
        assertEquals("answer2",s1.getAnswerOptions().get(1).getContent());
        assertEquals("answer3",s1.getAnswerOptions().get(2).getContent());

        //test related user
        assertEquals(u1.getEmail(),s1.getUser().getEmail());


    }

    @Test
    void createSurveySubmissionCorrectly() throws Exception{
        //prepare user
        createUserAndLogin();

        //create survey
        Survey s1=createSurveyforTesting();//new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        //parse data out of survey creation response
        JSONObject responseBody =new JSONObject(responseCreation.getContentAsString());

        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);

        //Load full survey
        s1=surveyService.findById(responseBody.getLong("id"));

        //setup a choice list for submission with the first provided answerOption of the survey
        List<AnswerOption> choices = new ArrayList<>();
        choices.add(s1.getAnswerOptions().get(0));

        //prepare submission and submit
        Submission submission = new Submission(null,s1.getId(), OffsetDateTime.now(),choices,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();

        //parse response in a JsonObject and load it from DB
        JSONObject responseJson=new JSONObject(responseCreation.getContentAsString());
        Submission submission1 =submissionService.findByID(responseJson.getLong("id"));



        //test API-Call response
        assertEquals(responseCreation.getStatus(),200);

        //test DB-data
        assertEquals(1,submission1.getChoices().size());
        assertEquals("answer1",submission1.getChoices().get(0).getContent());
        assertEquals("149.178.222.175",submission1.getParticipant().getIpAddress());



    }


    @Test
    void createSurveySubmissionNotCorrectly_submit2timesForOneSurvey() throws Exception{
        //prepare user
        createUserAndLogin();

        //create survey
        Survey s1=createSurveyforTesting();
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        //parse data out of survey creation response
        JSONObject responseBody =new JSONObject(responseCreation.getContentAsString());

        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);
        //Load full survey
        s1=surveyService.findById(responseBody.getLong("id"));

        //setup a choice list for submission with the first provided answerOption of the survey
        List<AnswerOption> choices = new ArrayList<>();
        choices.add(s1.getAnswerOptions().get(0));

        Submission submission = new Submission(null,s1.getId(), OffsetDateTime.now(),choices,participant);
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();


        //parse cookie out of the response of the first submission and safe is in a local property
        JSONObject responseSubmission = new JSONObject(responseCreation.getContentAsString());
        JSONObject cookieParticipant = new JSONObject(responseSubmission.getString("participant"));
        String cookie= cookieParticipant.getString("Cookie");

        //setup of a new submission, but with the already stored participant -> cookie as the identifier -> get participant by cookie
        submission = new Submission(null,s1.getId(), OffsetDateTime.now(),choices,participantService.getByCookieID(cookie).get(0));
        responseCreation=mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();


        //create a new json object with the response
        responseBody= new JSONObject(responseCreation.getContentAsString());


        //assert that the type is forbidden and the http response is 403 -> submission is not allowed two times
        assertEquals(403,responseCreation.getStatus());
        assertEquals("Forbidden",responseBody.getString("type"));
    }


    @Test
    void deleteSurveyById() throws Exception{
        //login User and create a survey for testing
        createUserAndLogin();
        Survey s1=createSurveyforTesting();
        s1 =surveyService.addOrUpdateSurvey(s1);
        boolean proofIfSurveyIsNullBeforeDelete = surveyService.findById(s1.getId())==null;

        //delete survey
        MockHttpServletResponse responseCreation=mockMvc.perform(delete("/survey/{id}",s1.getId()).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();
        boolean proofIfSurveyIsNullAfterDelete = surveyService.findById(s1.getId())==null;

        //assert that the survey exists before delete and not exist after
        assertEquals(200,responseCreation.getStatus());
        Assertions.assertTrue(proofIfSurveyIsNullAfterDelete);
        Assertions.assertFalse(proofIfSurveyIsNullBeforeDelete);

    }


    @Test
    void getSurveyById() throws Exception{
        //login User and create a survey for testing
        createUserAndLogin();
        Survey s1=createSurveyforTesting();
        s1 =surveyService.addOrUpdateSurvey(s1);
        MockHttpServletResponse responseCreation=mockMvc.perform(get("/survey/{id}",s1.getId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();


        // take the response and parse it into a jsonObject
        JSONObject responseJson= new JSONObject(responseCreation.getContentAsString());


        //test response values
        assertEquals("Hallo",responseJson.getString("name"));
        assertEquals("Hallo?",responseJson.getString("questionText"));
        assertEquals("#FFFFFF",responseJson.getString("backgroundColor"));
        assertEquals("#EEEEEE",responseJson.getString("accentColor"));
        assertEquals("nps",responseJson.getString("mode"));
        assertEquals(200,responseCreation.getStatus());

        //parse answer options for testing
        JSONArray answerOptionsJson=responseJson.getJSONArray("answerOptions");

        // test answerOptions
        assertEquals("answer1",answerOptionsJson.getJSONObject(0).getString("content"));
        assertEquals("answer2",answerOptionsJson.getJSONObject(1).getString("content"));
        assertEquals("answer3",answerOptionsJson.getJSONObject(2).getString("content"));

        //parse user for testing
        responseJson=responseJson.getJSONObject("user");

        //test user
        assertEquals("123@gmx.de",responseJson.getString("email"));
        assertEquals("bbb",responseJson.getString("firstName"));
        assertEquals("ccc",responseJson.getString("lastName"));

    }


    @Test
    void getSurveySubmissionsById() throws Exception {
        //prepare user
        createUserAndLogin();

        //create survey with the function and get the answerOptions
        Survey s1=createSurveyforTesting();
        List<AnswerOption> answerOptionList=s1.getAnswerOptions();
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        //parse data out of survey creation response
        JSONObject responseBody =new JSONObject(responseCreation.getContentAsString());

        //create a new participant
        Participant participant = new Participant(null,"149.178.222.175",null);
        //Load full survey
        Survey survey=surveyService.findById(responseBody.getLong("id"));

        //set choices of submission due to clear existing answerOptions list and add one of the loaded survey as a choice
        answerOptionList.clear();
        answerOptionList.add(survey.getAnswerOptions().get(0));

        Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();


        //setup of a new submission
        participant.setIpAddress("168.178.192.3");
        submission = new Submission(null,survey.getId(), OffsetDateTime.now(),answerOptionList,participant);
        mockMvc.perform(post("/survey/{id}/submission",responseBody.getString("id")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();

        //get all submissions
        responseCreation=mockMvc.perform(get("/survey/{id}/submissions",responseBody.getString("id")).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(survey))).andReturn().getResponse();

        assertEquals(responseCreation.getStatus(),200);

    }

    @Test
    void updateSurveyById() throws Exception {
        //prepare user
        createUserAndLogin();

        //create survey
        Survey s1=createSurveyforTesting();
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

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
        responseCreation=mockMvc.perform(post("/survey/{id}",updatedSurvey.getId()).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(updatedSurvey))).andReturn().getResponse();

        //parsing the response
        responseBody2 =new JSONObject(responseCreation.getContentAsString());

        //test the results -> compared with first survey object : all of the changes means that properties will be not equal compared to each other
        Assertions.assertNotEquals(s1.getName(),responseBody2.getString("name"));
        Assertions.assertNotEquals(s1.getQuestionText(),responseBody2.getString("questionText"));
        Assertions.assertNotEquals(s1.getBackgroundColor(),responseBody2.getString("backgroundColor"));
        Assertions.assertNotEquals(s1.getAccentColor(),responseBody2.getString("accentColor"));
    }

    private void createUserAndLogin() throws Exception{
        u1= new User("123@gmx.de","aaa","bbb","ccc");


        //add password property for Registration
        JSONObject jsonObjectRegister= new JSONObject(mapper.writeValueAsString(u1));
        jsonObjectRegister.put("password","aaa");

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString()));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString())).andReturn().getResponse();

        //parse authKey
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        authKey=responseBody.getString("authKey");

    }

    private Survey createSurveyforTesting(){
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));
        return new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
    }
}