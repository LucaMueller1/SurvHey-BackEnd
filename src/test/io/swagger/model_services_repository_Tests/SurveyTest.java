package io.swagger.model_services_repository_Tests;

import io.swagger.model.AnswerOption;
import io.swagger.model.Survey;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import io.swagger.services.SurveyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class SurveyTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;



    Random r = new Random();
    //Needed Userproperties
    List<User> users= new ArrayList<>();

    int amount_of_users = 0;


    @Test
    public void createSurveyTest(){
        this.createUsers();

        int amount_of_surveys =100;



        List<Survey> survey= new ArrayList<>();
        List<AnswerOption> answerOptions= new ArrayList<>();
        List<Integer> amount_per_Survey = new ArrayList<>();

        //Erstellung der Survey Objekte
        for (int i =0; i<amount_of_surveys;i++){

            //Maximal 10 unterschiedliche Antwortmöglichkeiten
            int amount_answer_options =r.nextInt(9)+1;

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



        //hole die Surveys wieder aus der DB und vergleiche
        for(int i = 0; i<survey.size();i++){

            Survey currentS= surveyService.findById(survey.get(i).getId());

            //Test, ob gleiche Anzahl Antwortmöglichkeiten zurückgeliefert werden
            assertEquals(amount_per_Survey.get(i),currentS.getAnswerOptions().size());

            //test the content of surveys to see if the data is stored in the DB correctly
            assertEquals(survey.get(i).getUser().toString(),currentS.getUser().toString());
            assertEquals(survey.get(i).getName(),currentS.getName());
            assertEquals(survey.get(i).getBackgroundColor(),currentS.getBackgroundColor());
            assertEquals(survey.get(i).getAccentColor(),currentS.getAccentColor());
            assertEquals(survey.get(i).getMode(),currentS.getMode());
            assertEquals(survey.get(i).getQuestionText(),currentS.getQuestionText());


        }
    }



    public void createUsers(){




            amount_of_users = 50;



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