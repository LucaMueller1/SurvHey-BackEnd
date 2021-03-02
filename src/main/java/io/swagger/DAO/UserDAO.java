package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Entity
@Table(name="SurvHey_DB.User")
public class UserDAO implements Serializable {

    @Id
    @Column(name = "E_Mail")
    private String eMail;

    @Column(name = "Password")
    private String Password;



    //Constructors

    public UserDAO(String e_Mail, String password) {
        eMail = e_Mail;
        Password = password;
    }

    public UserDAO() {
        eMail = null;
        Password = null;
    }


    //getter & setter

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }



}
