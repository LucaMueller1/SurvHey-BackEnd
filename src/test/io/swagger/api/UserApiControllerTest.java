package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.User;
import io.swagger.repository.*;
import io.swagger.services.AuthService;
import io.swagger.services.ParticipantService;
import io.swagger.services.SubmissionService;
import io.swagger.services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*@ExtendWith(SpringExtension.class)*/
@WebMvcTest
@RunWith(SpringRunner.class)
//@SpringBootTest
class UserApiControllerTest {


    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;


    @MockBean
    private UserService personService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SurveyRepository sr;

    @MockBean
    private SubmissionRepository SubR;

    @MockBean
    private SubmissionService SS;

    @MockBean
    private AuthKeyRepository akr;

    @MockBean
    private AuthService as;

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private ParticipantRepository pr;


    private JacksonTester<User> jsonTester;

    private User userModel;

   /* @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        userModel = new User();
    }*/

    @Test
    void createUser() throws Exception {

        JacksonTester.initFields(this, objectMapper);
        userModel = new User("aaa","bbb","ccc","ddd");


       /* MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("email","aaa");
        request.addParameter("password","bbb");
        request.addParameter("firstName","ccc");
        request.addParameter("lastName","ddd");
        request.*/

        final String personDTOJson = jsonTester.write(userModel).getJson();
        //MockHttpServletRequestBuilder as = new MockHttpServletRequestBuilder(post(new URI()));
        System.out.println(mockMvc
                .perform(post("http://localhost:8080/v2").content(personDTOJson).contentType(APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString());
                //.andExpect(status().isCreated()));

        System.out.println(personService.findByEmail("aaa"));



        }




    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void logoutUser() {
    }
}