package io.swagger.SystemTest;


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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SystemTest {
//one test method, approach: create users -> login Users -> create surveys -> submit -> analyze survey submissions
// All data is random generated
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

    List<User> userList = new ArrayList<>();

    Map<User,String> userListWithAuthKey=new HashMap<>();

    List<Survey> surveys = new ArrayList<>();

    List<Survey> createdSurveys= new ArrayList<>();

    List<Submission> submissionList= new ArrayList<>();

    List<Submission> createdSubmissions= new ArrayList<>();

    //list for a list with chosen answerOptions
    List<List<AnswerOption>> answerOptionsListForSubmissions = new ArrayList<>();

    List<MockHttpServletResponse> responsesOfAPICalls= new ArrayList<>();


    int amountofUsers=0;

    int amountOfSurveys=0;



    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }


    @Test
    public void SystemTest() throws Exception{

        generateUsers(5);

        MockHttpServletResponse response=null;
        JSONObject jsonObjectForCreatedUser;


        //create user and login -> safe in Map user & authKey
        for(int i = 0 ; i<amountofUsers;i++){

            response=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userList.get(i)))).andReturn().getResponse();

            //test if the user is inside the db
            assertEquals(response.getStatus(),200);
            assertNotNull(userService.findByEmail(userList.get(i).getEmail()));

            //login current user
            response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userList.get(i)))).andReturn().getResponse();

            //create jsonObject to parse authKey
            jsonObjectForCreatedUser= new JSONObject(response.getContentAsString());

            //test response code and if API-Call returns an authKey
            assertEquals(response.getStatus(),200);
            assertNotNull(jsonObjectForCreatedUser.getString("authKey"));


            //add user and authKey to the map
            userListWithAuthKey.put(userService.findByEmail(userList.get(i).getEmail()),jsonObjectForCreatedUser.getString("authKey"));

        }

        //generate surveys
        generateSurveys(10);


        //create a list to store the created surveys

        for(int i = 0 ; i<amountOfSurveys;i++){

            String authKey=userListWithAuthKey.get(userService.findByEmail(surveys.get(i).getUser().getEmail()));
            System.out.println(authKey);
            MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(surveys.get(i)))).andReturn().getResponse();

            // take the response and parse it into a jsonObject
            JSONObject responseJson= new JSONObject(responseCreation.getContentAsString());

            //test if survey is stored in the db
            assertNotNull(surveyService.findById(responseJson.getLong("id")));
            assertEquals(200,responseCreation.getStatus());

            //add the whole survey to created surveys list
            createdSurveys.add(surveyService.findById(responseJson.getLong("id")));

        }

        System.out.println(createdSurveys.toString());


        //create submissions
        generateSubmissions(1000);

        for(int i =0 ; i<submissionList.size();i++){


            response=mockMvc.perform(post("/survey/{id}/submission",submissionList.get(i).getSurveyId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submissionList.get(i)))).andReturn().getResponse();
            JSONObject responseJson=new JSONObject(response.getContentAsString());

            //load created submission and store it into the created submissions List
            createdSubmissions.add(submissionService.findByID(responseJson.getLong("id")));

        }
        System.out.println(createdSubmissions.toString());


        //get results and analysis
        System.out.println("*********************************************************************");
        for(int i = 0 ; i<createdSurveys.size();i++){
            response=mockMvc.perform(get("/survey/{id}/results",createdSurveys.get(i).getId()).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

            System.out.println(response.getContentAsString());

            response=mockMvc.perform(get("/survey/{id}/analysis",createdSurveys.get(i).getId()).header("api_key",userListWithAuthKey.get(userService.findByEmail(createdSurveys.get(i).getUser().getEmail()))).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


            System.out.println(response.getContentAsString());



        }




        /*
        System.out.println(userList.toString());
        System.out.println("***********************************************************");
        System.out.println(surveys.toString());
        System.out.println(surveys.get(0).getAnswerOptions().get(0).getContent());
        */
        //Problem möglicherweise: AnswerOptions landen nicht im Survey in der funktion generate Surveys

    }


    private void generateSubmissions(int amountOfSubmissions){

        for (int i =0; i<amountOfSubmissions;i++){

            //choose a random survey
            Survey survey = createdSurveys.get(r.nextInt(amountOfSurveys));

            //choose a random AnswerOption of the survey and put it into a temporary AnswerOption List
            AnswerOption answerOption= survey.getAnswerOptions().get(r.nextInt(survey.getAnswerOptions().size()));
            List<AnswerOption> tempAnswerOption =new ArrayList<>();
            tempAnswerOption.add(answerOption);
            answerOptionsListForSubmissions.add(tempAnswerOption);

            //create Participant with random generated IPv4-Address
            Participant participant = new Participant(null,92+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255),null);

            //create a new Submission
            Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),tempAnswerOption,participant);

            System.out.println(submission.toString());
            submissionList.add(submission);


        }






    }


    private void generateUsers(int amountOfUserToCreate){
        amountofUsers=amountOfUserToCreate;

        for(int i =0; i<amountOfUserToCreate;i++) {
            userList.add(new User(stringGenerator(45), stringGenerator(r.nextInt(30)), stringGenerator(r.nextInt(20)), stringGenerator(r.nextInt(35))));
        }

    }

    private void generateSurveys(int amountOfSurveysToCreate){



        amountOfSurveys=amountOfSurveysToCreate;



        for(int i =0; i<amountOfSurveysToCreate;i++) {

           Survey s1= new Survey(null,stringGenerator(45), stringGenerator(r.nextInt(100)),"nps", stringGenerator(8), stringGenerator(8),userList.get(r.nextInt(amountofUsers)),new ArrayList<>());
            //create answerOptions
            int amountOfAnswerOptions=r.nextInt(9)+2;

            for (int j = 0 ; j<amountOfAnswerOptions;j++){
                s1.addAnswerOptionsItem(new AnswerOption(null,null,stringGenerator(25)));
            }

            surveys.add(s1);
        }

    }


    private String stringGenerator(int len){
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!§$%&/()=?*@'+#,.-;:_ ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = len;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        return sb.toString();

    }

}
