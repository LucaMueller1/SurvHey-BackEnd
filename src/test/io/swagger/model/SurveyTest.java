package io.swagger.model;

import io.swagger.repository.SurveyRepository;
import io.swagger.repository.UserRepository;
import io.swagger.services.SurveyService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SurveyTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    Random r = new Random();
    //Needed Userproperties
    List<User> users= new ArrayList<>();

    int amount_of_users = 0;


    @Test
    public void createSurveyTest(){
        this.createUsers();

        int amount_of_surveys =0;

        while (amount_of_surveys==0){
        amount_of_surveys = r.nextInt(100);
        }
        System.out.println("Surveys: "+amount_of_surveys);
        System.out.println("Users: "+ amount_of_users);
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



        //hole die Surveys wieder aus der DB und vergleiche
        for(int i = 0; i<survey.size();i++){

            Survey currentS= surveyService.findById(survey.get(i).getId());
            System.out.println("DB: "+currentS.toString());
            //System.out.println("DB: "+currentS.getAnswerOptions().toString());
            //Test, ob gleiche Anzahl Antwortmöglichkeiten zurückgeliefert werden
            assertEquals(amount_per_Survey.get(i),currentS.getAnswerOptions().size());

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