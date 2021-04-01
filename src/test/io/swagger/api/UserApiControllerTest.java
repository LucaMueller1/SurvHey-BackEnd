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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserApiControllerTest {


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

        //assert response code 200 and the user is stored in the db
        assertEquals(response.getStatus(),200);
        assertNotNull(userService.findByEmail("123@gmx.de"));
        }


    @Test
    void deleteUser() throws Exception{
        User u1= new User("123@gmx.de","aaa","bbb","ccc");

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        JSONObject jsonObject=new JSONObject(response.getContentAsString());

        String authKey= jsonObject.getString("authKey");

        mockMvc.perform(delete("/user").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();


        //assert that the user is not in the db anymore
        assertNull(userService.findByEmail("123@gmx.de"));

    }

    @Test
    void getUser() throws Exception{

        User u1= new User("123@gmx.de","aaa","bbb","ccc");

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        JSONObject jsonObject=new JSONObject(response.getContentAsString());

        String authKey= jsonObject.getString("authKey");
        response=mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON).header("api_key",authKey).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //parse response in a jsonObject and test
        JSONObject userResponseJson= new JSONObject(response.getContentAsString());
        assertEquals("123@gmx.de",userResponseJson.getString("email"));
        assertEquals("bbb",userResponseJson.getString("firstName"));
        assertEquals("ccc",userResponseJson.getString("lastName"));

    }

    @Test
    void loginUserCorrectly() throws Exception {
        User u1= new User("123@gmx.de","aaa","bbb","ccc");

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        JSONObject responseBody = new JSONObject(response.getContentAsString());

        //test response code and if API-Call returns an authKey
        assertEquals(response.getStatus(),200);
        assertNotNull(responseBody.getString("authKey"));

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
    void logoutUser() throws Exception{
        User u1= new User("123@gmx.de","aaa","bbb","ccc");

        //create User
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1)));

        //login User
        MockHttpServletResponse response=mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();
        JSONObject jsonObject=new JSONObject(response.getContentAsString());

        String authKey= jsonObject.getString("authKey");


        response=mockMvc.perform(get("/user/logout").header("api_key",authKey).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(u1))).andReturn().getResponse();

        //assert HTTP-Response code 200
        assertEquals(200,response.getStatus());
    }
}

