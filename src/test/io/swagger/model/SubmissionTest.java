package io.swagger.model;

import io.swagger.repository.SurveyRepository;
import io.swagger.repository.UserRepository;
import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Part;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class SubmissionTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SubmissionService submissionService;

    Random r = new Random();
    //Needed Userproperties
    List<User> users= new ArrayList<>();

    List<Survey> surveys = new ArrayList<>();

    int amount_of_users = 0;

    int amount_of_surveys =0;



    @Test
    public void SubmitTest(){
        this.createUsers();
        this.createSurveys();

        //Load Surveys of the Database
        for (long i=1; i<=amount_of_surveys;i++){
            surveys.add(surveyService.findById(i));

            }
        int amount_of_submissions =0;
        while (amount_of_submissions==0){
            amount_of_submissions=r.nextInt(1000);
        }
        System.out.println("Submissions: "+ amount_of_submissions);
        System.out.println("Survey: "+ amount_of_surveys);
        for(int j =0; j<= amount_of_submissions;j++){
        //Prepare submission
        //Choose a random survey for a submission
        int randomSurveyChoice = r.nextInt(amount_of_surveys);



        //get the size of possible answer options of this survey
        int sizeOfAnswerOptionsOfASurvey = surveys.get(randomSurveyChoice).getAnswerOptions().size();

        //get random number of how many answers are choosen in the submission
        //int randomAnswerOptionChoice = r.nextInt(sizeOfAnswerOptionsOfASurvey)+1;

        //add the selected answer option to the list of choosen answerOption for this submission
        int randomChoice = r.nextInt(sizeOfAnswerOptionsOfASurvey);
        List<AnswerOption> Answer_choices= new ArrayList<>();
        surveys.get(randomSurveyChoice).toString();
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

    while (amount_of_surveys==0){
        amount_of_surveys = r.nextInt(100);
    }


    List<Survey> survey= new ArrayList<>();
    List<AnswerOption> answerOptions= new ArrayList<>();
    List<Integer> amount_per_Survey = new ArrayList<>();

    //Erstellung der Survey Objekte
    for (int i =0; i<amount_of_surveys;i++){

        //Maximal 10 unterschiedliche Antwortmöglichkeiten
        int amount_answer_options =r.nextInt(9)+1;
        System.out.println("Answer options: "+amount_answer_options);
        amount_per_Survey.add(amount_answer_options);

        //Erstellung der Antwortmöglichkeiten
        for(int j = 0; j<amount_answer_options; j++){
            AnswerOption answerOption = new AnswerOption(null,null,stringGenerator(100));

            answerOptions.add(answerOption);
        }

        //Zufällige Auswahl des erstellenden Users
        int user = r.nextInt(users.size() -1);


        //Erstellung des Surveys
        Survey SurNew= new Survey(null,stringGenerator(50),stringGenerator(50),"nps",null,null,users.get(user),answerOptions);
        survey.add(SurNew);
        surveyService.addOrUpdateSurvey(SurNew);

        //Leeren der AnswerOptions
        answerOptions.clear();
    }



}

    public void createUsers(){



        while(amount_of_users==0) {
            amount_of_users = r.nextInt(1000);

        }
        System.out.println("Users: "+amount_of_users);
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