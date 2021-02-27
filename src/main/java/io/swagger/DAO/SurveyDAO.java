package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "SurvHey_DB.Survey")
public class SurveyDAO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Survey_ID")
    private long Survey_Id;

    @Column(name = "Survey_Name")
    private String Survey_Name;

    @Column(name = "User_ID")
    private long User_ID;

    @OneToMany(mappedBy = "Survey")
    private List<Survey_QuestionDAO> survey_QuestionDAOS;



    //Constructors
    public SurveyDAO(long survey_Id, String survey_Name, long user_ID, ArrayList<Survey_QuestionDAO> survey_QuestionDAOS) {
        Survey_Id = survey_Id;
        Survey_Name = survey_Name;
        User_ID = user_ID;
        this.survey_QuestionDAOS = survey_QuestionDAOS;
    }

    public SurveyDAO() {
        Survey_Id = -1;
        Survey_Name = null;
        User_ID = -1;
        survey_QuestionDAOS = null;
    }



    //getter & setter

    public long getSurvey_Id() {
        return Survey_Id;
    }

    public void setSurvey_Id(long survey_Id) {
        Survey_Id = survey_Id;
    }

    public String getSurvey_Name() {
        return Survey_Name;
    }

    public void setSurvey_Name(String survey_Name) {
        Survey_Name = survey_Name;
    }

    public long getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(long user_ID) {
        User_ID = user_ID;
    }

    public List<Survey_QuestionDAO> getSurvey_Questions() {
        return survey_QuestionDAOS;
    }

    public void setSurvey_Questions(ArrayList<Survey_QuestionDAO> survey_QuestionDAOS) {
        this.survey_QuestionDAOS = survey_QuestionDAOS;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
