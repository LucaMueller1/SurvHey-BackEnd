package io.swagger.model_services_repository_Tests;

import io.swagger.model.*;
import io.swagger.repository.SurveyRepository;
import io.swagger.repository.UserRepository;
import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SubmissionTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private SubmissionService submissionService;

    Random r = new Random();
    //Needed Userproperties
    List<User> users= new ArrayList<>();

    List<Survey> surveys = new ArrayList<>();

    int amount_of_users = 0;

    int amount_of_surveys =0;



    @Test
    public void SubmitTest() throws InterruptedException {
        this.createUsers();
        this.createSurveys();

        List<Survey> surveyList= new ArrayList<>();
        //Load Surveys of the Database
        for (int i=1; i<=amount_of_surveys;i++){
            surveyList.add(surveyService.findById(surveys.get(i-1).getId()));

            }
        surveys=surveyList;

        int amount_of_submissions =200;

        for(int j =0; j<= amount_of_submissions;j++){
        //Prepare submission
        //Choose a random survey for a submission
        int randomSurveyChoice = r.nextInt(amount_of_surveys-1);


        //get the size of possible answer options of this survey
        List<AnswerOption> options = surveys.get(randomSurveyChoice).getAnswerOptions();

        int sizeOfAnswerOptionsOfASurvey = options.size();

        //get random number of how many answers are choosen in the submission
        //int randomAnswerOptionChoice = r.nextInt(sizeOfAnswerOptionsOfASurvey)+1;

        //add the selected answer option to the list of choosen answerOption for this submission
        int randomChoice = r.nextInt(sizeOfAnswerOptionsOfASurvey);
        List<AnswerOption> Answer_choices= new ArrayList<>();
        Answer_choices.add(surveys.get(randomSurveyChoice).getAnswerOptions().get(randomChoice));

        //create a participant
        Participant participant= new Participant(null,r.nextInt(256)+"."+r.nextInt(256)+"."+r.nextInt(256)+"."+r.nextInt(256),stringGenerator(35));

        //create a new participant
        submissionService.addOrUpdateSubmission(new Submission(null,surveys.get(randomSurveyChoice).getId(), OffsetDateTime.now(),Answer_choices,participant)).toString();

        //get all Submissions of this survey

        submissionService.findAllBySurveyID((long) randomSurveyChoice).toString();

    }



    }

public void createSurveys(){

    amount_of_surveys = 50;
    List<Survey> survey= new ArrayList<>();
    List<AnswerOption> answerOptions= new ArrayList<>();
    List<Integer> amount_per_Survey = new ArrayList<>();

    //Erstellung der Survey Objekte
    for (int i =0; i<amount_of_surveys;i++){

        //Maximal 10 unterschiedliche Antwortmöglichkeiten
        int amount_answer_options =r.nextInt(8)+2;
        amount_per_Survey.add(amount_answer_options);


        //Zufällige Auswahl des erstellenden Users
        int user = r.nextInt(users.size() -1);

        //Erstellung des Surveys
        Survey SurNew= new Survey(null,stringGenerator(50),stringGenerator(50),"nps",null,null,users.get(user),answerOptions);


        //Erstellung der Antwortmöglichkeiten
        for(int j = 0; j<amount_answer_options; j++){
            AnswerOption answerOption = new AnswerOption(null,null,stringGenerator(100));
            SurNew.addAnswerOptionsItem(answerOption);
            answerOptions.add(answerOption);
        }




      survey.add(SurNew);
        Survey newSurvey =surveyService.addOrUpdateSurvey(SurNew);
        surveys.add(newSurvey);

        //Leeren der AnswerOptions
        answerOptions.clear();

    }
    amount_of_surveys=survey.size();



}

    public void createUsers(){

        amount_of_users = 25;


        for(int i = 0; i<amount_of_users; i++){
            int rand = r.nextInt(44)+1;
            users.add(new User(stringGenerator(45),stringGenerator(rand),stringGenerator(rand),stringGenerator(rand)));

        }

        Iterator<User> iterUser= users.iterator();
        while(iterUser.hasNext()){
            userRepository.save(iterUser.next());
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