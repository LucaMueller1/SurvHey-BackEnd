package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.services.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserApiControllerTest {


    @Autowired
    private AuthService authService;
    @Autowired
    private GeoLocationService geoLocationService;

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


    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void createUser() throws Exception {
        User u1= new User("123@gmx.de","aaa","bbb","ccc");

        MockHttpServletResponse response=mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        assertEquals(response.getStatus(),200);

        }


    @Test
    void deleteUser() throws Exception{
        User u1= new User("123@gmx.de","aaa","bbb","ccc");
        System.out.println(mapper.writeValueAsString(u1));

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        JSONObject jsonObject=new JSONObject(response.getContentAsString());
        System.out.println(response.getContentAsString());
        String authKey= jsonObject.getString("authKey");
        System.out.println(authKey);


    }

    @Test
    void getUser() {
    }

    @Test
    void loginUserCorrectly() throws Exception {
        User u1= new User("123@gmx.de","aaa","bbb","ccc");
        System.out.println(mapper.writeValueAsString(u1));

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        JSONObject responseBody = new JSONObject(response.getContentAsString());
        System.out.println(responseBody.getString("authKey"));
        assertEquals(response.getStatus(),200);


    }
    @Test
    void loginUserNotCorrectly_wrongPassword() throws Exception {
        User u1= new User("123@gmx.de","aaa","bbb","ccc");
        System.out.println(mapper.writeValueAsString(u1));


        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //wrong password for login
        User u2= new User("123@gmx.de","bbb","bbb","ccc");

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u2))).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        System.out.println(response.getStatus());


        JSONObject responseBody = new JSONObject(response.getContentAsString());

        //assert that the access is forbidden
        assertEquals(response.getStatus(),403);
        assertEquals(responseBody.getString("type"),"Forbidden");

    }



    @Test
    void logoutUser() {


    }
}

