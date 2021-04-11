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
import springfox.documentation.spring.web.json.Json;

import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
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


        generateUsers(100);

        MockHttpServletResponse response=null;
        JSONObject jsonObjectForCreatedUser;


        //create user and login -> safe in Map user & authKey
        for(int i = 0 ; i<amountofUsers;i++){


            //add password property for Registration
            JSONObject jsonObjectRegister= new JSONObject(mapper.writeValueAsString(userList.get(i)));
            jsonObjectRegister.put("password",userList.get(i).getPassword());



            response=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString())).andReturn().getResponse();

            //test if the user is inside the db
            assertEquals(response.getStatus(),200);
            assertNotNull(userService.findByEmail(userList.get(i).getEmail()));

            //login current user
            response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonObjectRegister.toString())).andReturn().getResponse();
            //System.out.println(response.getContentAsString());
            //create jsonObject to parse authKey
            jsonObjectForCreatedUser= new JSONObject(response.getContentAsString());

            //test response code and if API-Call returns an authKey
            assertEquals(response.getStatus(),200);
            assertNotNull(jsonObjectForCreatedUser.getString("authKey"));


            //add user and authKey to the map
            userListWithAuthKey.put(userService.findByEmail(userList.get(i).getEmail()),jsonObjectForCreatedUser.getString("authKey"));

        }

        //generate surveys
        generateSurveys(100);


        for(int i = 0 ; i<amountOfSurveys;i++){

            String authKey=userListWithAuthKey.get(userService.findByEmail(surveys.get(i).getUser().getEmail()));

            MockHttpServletResponse responseCreation=mockMvc.perform(post("/survey").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(surveys.get(i)))).andReturn().getResponse();

            // take the response and parse it into a jsonObject
            JSONObject responseJson= new JSONObject(responseCreation.getContentAsString());

            //test if survey is stored in the db -> load survey from DB -> compare it with the parsed data

            Survey dbSurvey = surveyService.findById(responseJson.getLong("id"));
            assertNotNull(dbSurvey);

            //test response
            JSONObject js=new JSONObject(responseJson.getString("user"));
            //test if password is responded
            assertFalse(js.has("password"));

            assertEquals(200,responseCreation.getStatus());


            //test values from db -> compare parsed data with database data
            assertEquals(surveys.get(i).getName(),dbSurvey.getName());
            assertEquals(surveys.get(i).getQuestionText(),dbSurvey.getQuestionText());
            assertEquals(surveys.get(i).getBackgroundColor(),dbSurvey.getBackgroundColor());
            assertEquals(surveys.get(i).getAccentColor(),dbSurvey.getAccentColor());

            //test related answerOptions
            int AmountAnswerOptionsOfSurvey= surveys.get(i).getAnswerOptions().size();
            // test if all AnswerOptions are stored in the DB
            assertEquals(AmountAnswerOptionsOfSurvey,dbSurvey.getAnswerOptions().size());

            //Test of the content of answerOptions for each answerOptions
            for(int j = 0 ;j<AmountAnswerOptionsOfSurvey;j++){
                assertEquals(surveys.get(i).getAnswerOptions().get(j).getContent(),dbSurvey.getAnswerOptions().get(j).getContent());

            }

            //test related user
            assertEquals(surveys.get(i).getUser().getEmail(),dbSurvey.getUser().getEmail());


            //add the whole survey to created surveys list
            createdSurveys.add(surveyService.findById(responseJson.getLong("id")));

        }



        //create submissions
        generateSubmissions(10000);

        for(int i =0 ; i<submissionList.size();i++){


            response=mockMvc.perform(post("/survey/{id}/submission",submissionList.get(i).getSurveyId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(submissionList.get(i)))).andReturn().getResponse();
            JSONObject responseJson=new JSONObject(response.getContentAsString());

            assertEquals(200,response.getStatus());

            //load created submission
            Submission submission = submissionService.findByID(responseJson.getLong("id"));
            //add submission it into the created submissions List
            createdSubmissions.add(submission);

            //test if data is parsed correctly in the db
            assertEquals(submissionList.get(i).getChoices().size(),submission.getChoices().size());
            assertEquals(submissionList.get(i).getParticipant().getIpAddress(), submission.getParticipant().getIpAddress());

            //test choices content
            for (int j = 0 ; j< submissionList.get(i).getChoices().size(); j++) {
                assertEquals(submissionList.get(i).getChoices().get(j).getContent(), submission.getChoices().get(j).getContent());
            }

        }



        //get results and analysis
        for(int i = 0 ; i<createdSurveys.size();i++){
            response=mockMvc.perform(get("/survey/{id}/results",createdSurveys.get(i).getId()).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
            assertEquals(200,response.getStatus());

            List<Submission> submissionsOfCurrentSurvey = new ArrayList<>();

            //add submissions of current survey in a local list
            for (int j =0 ; j<submissionList.size();j++){

                if (submissionList.get(j).getSurveyId()==createdSurveys.get(i).getId()) {
                    submissionsOfCurrentSurvey.add(submissionList.get(j));
                }
            }

            //count Choices and compare it with the results
            for (int j = 0; j<createdSurveys.get(i).getAnswerOptions().size();j++){

                //iterate over all AnswerOptions of this survey
                AnswerOption currentAnswerOption=  createdSurveys.get(i).getAnswerOptions().get(j);
                int amountOfChoicesOfCurrentAnswerOption=0;


                for(int k =0 ; k<submissionsOfCurrentSurvey.size();k++){
                    //if a choice is equal to the current answerOption -> counter++

                    if(currentAnswerOption.getContent()==submissionsOfCurrentSurvey.get(k).getChoices().get(0).getContent()){
                        amountOfChoicesOfCurrentAnswerOption++;

                    }
                }

                //parse response into a JsonObject for assertEquals
                JSONObject jsonResults = new JSONObject(response.getContentAsString());
                jsonResults=new JSONObject(jsonResults.getString("choices"));

                //test every answerOption if response submission amount is correct

                assertEquals(jsonResults.getInt(currentAnswerOption.getContent()),amountOfChoicesOfCurrentAnswerOption);

            }

            response=mockMvc.perform(get("/survey/{id}/analysis",createdSurveys.get(i).getId()).header("api_key",userListWithAuthKey.get(userService.findByEmail(createdSurveys.get(i).getUser().getEmail()))).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        }

        /*while (true){
            Thread.sleep(100000000);

        }*/


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
            Participant participant = new Participant(null,88+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255),null);

            //create a new Submission
            Submission submission = new Submission(null,survey.getId(), OffsetDateTime.now(),tempAnswerOption,participant);

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
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!$%&/()=?*@'+#,.-;:_ ";

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
