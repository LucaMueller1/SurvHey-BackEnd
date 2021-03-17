package io.swagger.model;

import io.swagger.*;

import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
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
        SurveyService SS= new SurveyService();
        US.createUser(TestUser);
        TestUser = US.findByEmail("haha@haha");

        List<AnswerOption> aO=new ArrayList<>();
        List<AnswerOption> aO2=new ArrayList<>();
        aO.add(new AnswerOption(null,null,"one"));
        aO.add(new AnswerOption(null,null,"two"));
        aO.add(new AnswerOption(null,null,"three"));

        aO2.add(aO.get(1));
        Survey survey= new Survey(null,"test","test?",null,null,null,TestUser,aO);
        SS.addOrUpdateSurvey(survey);
        survey= SS.findById(new Long(1));
        submissionTest=new Submission(null,survey.getId(),null,aO2,null);

        SubmissionService SubS=new SubmissionService();

        submissionTest=SubS.addOrUpdateSubmission(submissionTest);
        submissionTest=SubS.findByID(submissionTest.getId());
    }

    @Test
    void id() {
    }

    @Test
    void getId() {
        assertEquals(1,submissionTest.getId());
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