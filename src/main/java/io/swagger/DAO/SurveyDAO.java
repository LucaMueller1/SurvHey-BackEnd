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
    private long id;

    @Column(name = "Survey_Name")
    private String SurveyName;

    @Column(name = "E_Mail")
    private String email;

    @Column(name = "Question_Text")
    private String QuestionText;

    @Column(name = "Answer_Mode")
    private String AnswerMode;

    @OneToMany
    @JoinColumn(name = "Survey_ID")
    private List<Answer_OptionDAO> SurveyAnswerOptions;



    //Constructors


    public SurveyDAO(long id, String surveyName, String email, String questionText, String answerMode, List<Answer_OptionDAO> surveyAnswerOptions) {
        this.id = id;
        SurveyName = surveyName;
        this.email = email;
        QuestionText = questionText;
        AnswerMode = answerMode;
        SurveyAnswerOptions = surveyAnswerOptions;
    }

    public SurveyDAO() {
        this.id = -1;
        SurveyName = null;
        this.email = null;
        QuestionText = null;
        AnswerMode = null;
        this.SurveyAnswerOptions = null;
    }
    //getter & setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurveyName() {
        return SurveyName;
    }

    public void setSurveyName(String surveyName) {
        SurveyName = surveyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public String getAnswerMode() {
        return AnswerMode;
    }

    public void setAnswerMode(String answerMode) {
        AnswerMode = answerMode;
    }

    public List<Answer_OptionDAO> getSurveyAnswerOptions() {
        return SurveyAnswerOptions;
    }

    public void setSurveyAnswerOptions(List<Answer_OptionDAO> surveyAnswerOptions) {
        SurveyAnswerOptions = surveyAnswerOptions;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
