package io.swagger.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.swagger.model.*;
import io.swagger.services.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SurveyAPIControllerResultsTest {


    @Autowired
    private SurveyService surveyService;



    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    private User u1;

    private Survey survey;

    private List<AnswerOption> choices = new ArrayList<>();

    private List<String> ipAddress = new ArrayList<>();

    private List<Participant> participants = new ArrayList<>();

    private String authKey;


    @Before
    public void init (){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getSurveyResultsById() throws Exception{
        //prepare this test with test data
        this.prepare();

        MockHttpServletResponse response=mockMvc.perform(get("/survey/{id}/results",survey.getId()).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        JSONObject jsonObject=new JSONObject(response.getContentAsString());

        JSONObject choices = new JSONObject(jsonObject.getString("choices"));

        int answer1=choices.getInt("answer1");
        int answer2=choices.getInt("answer2");
        int answer3=choices.getInt("answer3");

        int answer1ofList = countAnswerOptionsofAList(this.choices,"answer1");
        int answer2ofList = countAnswerOptionsofAList(this.choices,"answer2");
        int answer3ofList = countAnswerOptionsofAList(this.choices,"answer3");

        //expect 50 participation and the http response status 200
        assertEquals(50,answer1+answer3+answer2);
        assertEquals(200,response.getStatus());

        //Compare the created list of answerOptions and the responded count
        assertEquals(answer1ofList,answer1);
        assertEquals(answer2ofList,answer2);
        assertEquals(answer3ofList,answer3);
    }


    @Test
    void getSurveyAnalysisById() throws Exception{
        //prepare this test with test data
        this.prepare();
        MockHttpServletResponse response=mockMvc.perform(get("/survey/{id}/analysis",survey.getId()).header("api_key",authKey).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        assertEquals(200,response.getStatus());

    }

    void prepare() throws Exception{
        //prepare a survey

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

        //create answerOptions
        List<AnswerOption> answerOptionList=new ArrayList<>();
        answerOptionList.add(new AnswerOption(null,null,"answer1"));
        answerOptionList.add(new AnswerOption(null,null,"answer2"));
        answerOptionList.add(new AnswerOption(null,null,"answer3"));

        //create survey
        Survey s1=new Survey(null,"Hallo","Hallo?","nps","#FFFFFF","#EEEEEE",u1,answerOptionList);
        MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",responseBody.getString("authKey")).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(s1))).andReturn().getResponse();

        //parse data out of survey creation response
        responseBody =new JSONObject(responseCreation.getContentAsString());

        //Load full survey
        survey=surveyService.findById(responseBody.getLong("id"));



        Random r = new Random();
        //create 50 random Submissions
        for (int i = 0;i<50;i++){

            //generate a random number to set the choice of one answerOption -> set the bound to 2 to rest in index of answerOptions list
            int answer_choice_index= r.nextInt(3);


            AnswerOption answerOptionChoice= survey.getAnswerOptions().get(answer_choice_index);
            //add answer option to the list
            answerOptionList.add(answerOptionChoice);
            choices.add(answerOptionChoice);
            //create a local answerOption list for the submission (so that this list only contains one choice)
            List<AnswerOption> localAnswerOptionList = new ArrayList<>();
            localAnswerOptionList.add(answerOptionChoice);

            //generate a random ipv4 address for the participant
            String ip= 92+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255);

            //add ip address to the list
            ipAddress.add(ip);

            //create participant
            Participant participant = new Participant(null,ip,null);

            //create submission
            Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),localAnswerOptionList,participant);
            responseCreation=mockMvc.perform(post("/survey/{id}/submission",survey.getId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submission))).andReturn().getResponse();

            //parse the created participant out of the response that it can be stored in the participants list

            responseBody = new JSONObject(responseCreation.getContentAsString());
            ObjectReader objectReader = mapper.readerForUpdating(new Participant(null,null,null));
            Participant participantParsed = objectReader.readValue(responseBody.getString("participant"));

            //add participant to the list
            participants.add(participantParsed);

        }
    }
    private int countAnswerOptionsofAList(List<AnswerOption> answerOptions, String stringToCount){
        int count=0;
        for (AnswerOption current : answerOptions) {
            String content = current.getContent();

            if (content.equals(stringToCount)) {
                count++;
            }
        }
        return count;
    }

}
