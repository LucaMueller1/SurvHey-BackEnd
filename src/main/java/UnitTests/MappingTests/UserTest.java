package UnitTests.MappingTests;

import io.swagger.model.User;
import io.swagger.services.UserService;

import java.nio.charset.Charset;
import java.util.Random;

public class UserTest {

    private User user;


/*
    public void UserisStoredCorrectlyInDB(){


        Random r = new Random();
        int string_size=r.nextInt((100 - 1) + 1) + 1;
        int object_amount= r.nextInt((1000 - 1) + 1) + 1;

        //Generation of a String
        byte[] array = new byte[string_size]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        UserService userService = new UserService();
        userService.createUser();

        User user = new User(generatedString);


    }
*/

}
