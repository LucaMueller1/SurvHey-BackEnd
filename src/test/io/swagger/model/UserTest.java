package io.swagger.model;

import io.swagger.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTest {


    @Autowired
    private UserRepository userRepository;



    @Test
    public void createUserTest(){
        //Dieser Test testet sowohl den UserService+Repository als auch das User-Model für eine definierbare Menge an zu erstellenden Usern
        List<User> users= new ArrayList<>();
        Random r = new Random();
        int amount_of_users=0;


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

        for (int i =0 ; i<users.size();i++){
            System.out.println(users.get(i).toString());
            assertEquals(userRepository.findByEmail(users.get(i).getEmail()),users.get(i));



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