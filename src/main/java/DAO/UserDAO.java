package DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Entity
@Table(name="SurvHey_DB.User")
public class UserDAO implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "User_ID")
    private long User_ID;

    @Column(name = "E_Mail")
    private String E_Mail;

    @Column(name = "Password")
    private String Password;



    //Constructors

    public UserDAO(long user_ID, String e_Mail, String password) {
        User_ID = user_ID;
        E_Mail = e_Mail;
        Password = password;
    }

    public UserDAO() {
        User_ID = -1;
        E_Mail = null;
        Password = null;
    }


    //getter & setter

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_ID) {
        User_ID = user_ID;
    }

    public String getE_Mail() {
        return E_Mail;
    }

    public void setE_Mail(String e_Mail) {
        E_Mail = e_Mail;
    }

    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }



}
