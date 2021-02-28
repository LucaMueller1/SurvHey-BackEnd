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

    @Column(name = "E_Mail")
    private String E_Mail;

    @Column(name = "Question_Text")
    private String Question_Text;

    @Column(name = "Answer_Mode")
    private String Answer_Mode;

    @OneToMany
    @JoinColumn(name = "Survey_ID")
    private List<Answer_OptionDAO> survey_answer_options;



    //Constructors

    public SurveyDAO(long survey_Id, String survey_Name, String e_Mail, String question_Text, String answer_Mode, List<Answer_OptionDAO> survey_answer_options) {
        Survey_Id = survey_Id;
        Survey_Name = survey_Name;
        E_Mail = e_Mail;
        Question_Text = question_Text;
        Answer_Mode = answer_Mode;
        this.survey_answer_options = survey_answer_options;
    }

    public SurveyDAO() {
        Survey_Id = -1;
        Survey_Name = null;
        E_Mail = null;
        Question_Text = null;
        Answer_Mode = null;
        this.survey_answer_options = null;
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

    public String getE_Mail() {
        return E_Mail;
    }

    public void setE_Mail(String e_Mail) {
        E_Mail = e_Mail;
    }

    public String getQuestion_Text() {
        return Question_Text;
    }

    public void setQuestion_Text(String question_Text) {
        Question_Text = question_Text;
    }

    public String getAnswer_Mode() {
        return Answer_Mode;
    }

    public void setAnswer_Mode(String answer_Mode) {
        Answer_Mode = answer_Mode;
    }

    public List<Answer_OptionDAO> getSurvey_answer_options() {
        return survey_answer_options;
    }

    public void setSurvey_answer_options(List<Answer_OptionDAO> survey_answer_options) {
        this.survey_answer_options = survey_answer_options;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
