package io.swagger.api;

import io.swagger.model.AnswerOption;
import io.swagger.model.Submission;
import io.swagger.model.Survey;
import io.swagger.model.User;
import io.swagger.services.SubmissionService;
import io.swagger.services.SurveyService;
import io.swagger.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserApiControllerTest {

    Submission submissionTest;
    @BeforeEach
    void setUp() {

        User TestUser = new User("haha@haha", "123", "first", "last");
        UserService US = new UserService();
        SurveyService SS = new SurveyService();
        US.createUser(TestUser);
        TestUser = US.findByEmail("haha@haha");


    }

    @Test
    @DisplayName("")
    void createUserTest(){

    }
}