package io.swagger.DAO;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "SurvHey_DB.Auth_Token")
public class Auth_TokenDAO {
    @Id
    @GeneratedValue
    @Column(name = "User_ID")
    private long User_ID;

    @Column(name="Token")
    private String auth_Token;

    @Column(name = "Expiring_Date")
    private Date Expiring_Date;


    //Constructors

    public Auth_TokenDAO(long user_ID, String auth_Token, Date expiring_Date) {
        User_ID = user_ID;
        this.auth_Token = auth_Token;
        Expiring_Date = expiring_Date;
    }

    public Auth_TokenDAO() {
        User_ID = -1;
        this.auth_Token = null;
        Expiring_Date = null;
    }
    //getter and setter


    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_ID) {
        User_ID = user_ID;
    }

    public String getAuth_Token() {
        return auth_Token;
    }

    public void setAuth_Token(String auth_Token) {
        this.auth_Token = auth_Token;
    }

    public Date getExpiring_Date() {
        return Expiring_Date;
    }

    public void setExpiring_Date(Date expiring_Date) {
        Expiring_Date = expiring_Date;
    }
}
