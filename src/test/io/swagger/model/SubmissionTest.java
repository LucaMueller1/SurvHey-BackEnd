package io.swagger.model;

import io.swagger.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionTest {

    Submission submissionTest;
    @BeforeEach
    void setUp() {
        User TestUser = new User("haha@haha","123","first","last");
        UserService US=new UserService();
        US.createUser(TestUser);


        List<AnswerOption> aO=new ArrayList<>();
        aO.add(new AnswerOption(null,null,"one"));
        aO.add(new AnswerOption(null,null,"two"));
        aO.add(new AnswerOption(null,null,"three"));
        Survey testSurvey=new Survey(null,"test","test?",null,null,null,US.findByEmail("haha@haha"),aO);

        submissionTest=new Submission();
    }

    @Test
    void id() {
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void surveyId() {
    }

    @Test
    void getSurveyId() {
    }

    @Test
    void setSurveyId() {
    }

    @Test
    void timestamp() {
    }

    @Test
    void getParticipant() {
    }

    @Test
    void setParticipant() {
    }

    @Test
    void getTimestamp() {
    }

    @Test
    void setTimestamp() {
    }

    @Test
    void choices() {
    }

    @Test
    void addChoicesItem() {
    }

    @Test
    void getChoices() {
    }

    @Test
    void setChoices() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}