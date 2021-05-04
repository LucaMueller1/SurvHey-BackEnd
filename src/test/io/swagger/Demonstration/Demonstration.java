package io.swagger.Demonstration;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import io.swagger.services.ParticipantService;
import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
import io.swagger.services.UserService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
@AutoConfigureMockMvc
public class Demonstration {

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

    Random r = new Random();


    User user;

    String authKey="";

    List<Survey> surveyList = new ArrayList<>();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void demonstration() throws Exception{
        User userToCreate = new User("test@survhey.com","password","Surv","Hey");
        JSONObject jsonObjectRegister= new JSONObject(mapper.writeValueAsString(userToCreate));

        //add jsonProperty for registration
        jsonObjectRegister.put("password","password");

        //add User
        MockHttpServletResponse response=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString())).andReturn().getResponse();

        user=userService.findByEmail("test@survhey.com");


        //login  user
        response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString())).andReturn().getResponse();

        //parse authKey
        JSONObject jsonObjectForCreatedUser= new JSONObject(response.getContentAsString());
        authKey=jsonObjectForCreatedUser.getString("authKey");


        //create Surveys
        //1. -> Radio; 2.-> Checkbox

        //1. ->Radio:
        List<AnswerOption> answerOptionsRadio=new ArrayList<>();
        answerOptionsRadio.add(new AnswerOption(null,null,"Vanille"));
        answerOptionsRadio.add(new AnswerOption(null,null,"Schokolade"));
        answerOptionsRadio.add(new AnswerOption(null,null,"Haselnuss"));
        answerOptionsRadio.add(new AnswerOption(null,null,"Straciatella"));
        answerOptionsRadio.add(new AnswerOption(null,null,"Erdbeere"));

        Survey radioSurvey= new Survey(null,"Eissorte","Welche dieser Eissorten mögen Sie am liebsten?","check","#008a5e","#7dffb3",user,answerOptionsRadio);


        // create Survey and fetch response
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(radioSurvey))).andReturn().getResponse();
        JSONObject responseJson= new JSONObject(responseCreation.getContentAsString());

        radioSurvey= surveyService.findById(responseJson.getLong("id"));



        //2. ->Checkbox:
        List<AnswerOption> answerOptionsCheckbox=new ArrayList<>();
        answerOptionsCheckbox.add(new AnswerOption(null,null,"Java"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"JavaScript"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"C++"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"C#"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"C"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"Python"));
        answerOptionsCheckbox.add(new AnswerOption(null,null,"PHP"));

        Survey checkboxSurvey= new Survey(null,"Programmiersprachen","Welche dieser Programmiersprachen beherrschen Sie?","radio","#0C3A71","#F7E562",user,answerOptionsCheckbox);

        responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(checkboxSurvey))).andReturn().getResponse();
        responseJson= new JSONObject(responseCreation.getContentAsString());

        checkboxSurvey=surveyService.findById(responseJson.getLong("id"));


        //both surveys get 100 submissions
        int amountOfSubmissions=75;
        List<Submission> submissions= new ArrayList<>();

        //create submissions for radioSurvey
        for (int i =0; i<amountOfSubmissions;i++){

            //choose a random AnswerOption of the survey and put it into a temporary AnswerOption List
            AnswerOption answerOption= radioSurvey.getAnswerOptions().get(r.nextInt(radioSurvey.getAnswerOptions().size()));
            List<AnswerOption> tempAnswerOption =new ArrayList<>();
            tempAnswerOption.add(answerOption);


            //create Participant with random generated IPv4-Address
            Participant participant = new Participant(null,88+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255),null);

            //create a new Submission
            Submission submission = new Submission(null,radioSurvey.getId(), OffsetDateTime.now(),tempAnswerOption,participant);

            submissions.add(submission);

        }


        //create submissions for Checkbox Survey
        for (int i =0; i<amountOfSubmissions;i++){

            //choose a random AnswerOption of the survey and put it into a temporary AnswerOption List+
            //List to check if answer is already chosen
            List<AnswerOption> aoChecklist= new ArrayList<>();
            List<AnswerOption> tempAnswerOption =new ArrayList<>();
            //random number of chosen answerOptions
            for (int j=0; j<(r.nextInt(checkboxSurvey.getAnswerOptions().size()-1)+1);j++){
                AnswerOption answerOption= checkboxSurvey.getAnswerOptions().get(r.nextInt(checkboxSurvey.getAnswerOptions().size()));

                boolean alreadyVoted= false;
                //check every already chosen ao
                for(int k=0 ;k<tempAnswerOption.size();k++){
                    if(answerOption.getContent()==tempAnswerOption.get(k).getContent()){
                        alreadyVoted=true;
                    }
                }

                if(alreadyVoted==true){
                    continue;
                }
                else {
                    tempAnswerOption.add(answerOption);
                }

            }


            //create Participant with random generated IPv4-Address
            Participant participant = new Participant(null,88+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255),null);

            //create a new Submission
            Submission submission = new Submission(null,checkboxSurvey.getId(), OffsetDateTime.now(),tempAnswerOption,participant);

            submissions.add(submission);

        }

        for (int i = 0;i<submissions.size();i++){

            response=mockMvc.perform(post("/survey/{id}/submission",submissions.get(i).getSurveyId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submissions.get(i)))).andReturn().getResponse();
            assertEquals(200,response.getStatus());
        }

        //fetch results 1.-> radio 2.->Checkbox
        //1.radio:
        response=mockMvc.perform(get("/survey/{id}/results",radioSurvey.getId()).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //2. Check
        response=mockMvc.perform(get("/survey/{id}/results",checkboxSurvey.getId()).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        //fetch analysis 1.-> radio 2.->Checkbox
        //1.radio:
        response=mockMvc.perform(get("/survey/{id}/analysis",radioSurvey.getId()).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        //2. Check
        response=mockMvc.perform(get("/survey/{id}/analysis",checkboxSurvey.getId()).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        while (true){
            Thread.sleep(100000000);

        }
    }










}
