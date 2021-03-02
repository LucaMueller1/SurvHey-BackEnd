package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Entity
@Table(name = "SurvHey_DB.Answer_Option")
public class Answer_OptionDAO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Answer_Option_ID")
    private long AnswerOptionID;

    @Column(name = "Survey_ID")
    private long SurveyID;

    @Column(name = "Answer_Option")
    private String QuestionText;


    //Constructors


    public Answer_OptionDAO(long answerOptionID, long surveyID, String questionText) {
        AnswerOptionID = answerOptionID;
        SurveyID = surveyID;
        QuestionText = questionText;
    }

    public Answer_OptionDAO() {
        AnswerOptionID = -1;
        SurveyID = -1;
        QuestionText = null;
    }


    //getter & setter

    public long getAnswerOptionID() {
        return AnswerOptionID;
    }

    public void setAnswerOptionID(long answerOptionID) {
        AnswerOptionID = answerOptionID;
    }

    public long getSurveyID() {
        return SurveyID;
    }

    public void setSurveyID(long surveyID) {
        SurveyID = surveyID;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
