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

    @ManyToOne
    @JoinColumn(name = "Survey_ID", insertable = false, updatable = false)
    private SurveyDAO Survey;

    @Column(name = "Answer_Option")
    private String QuestionText;


    //Constructors


    public Answer_OptionDAO(long answerOptionID, SurveyDAO survey, String questionText) {
        AnswerOptionID = answerOptionID;
        this.Survey = survey;
        QuestionText = questionText;
    }

    public Answer_OptionDAO() {
        AnswerOptionID = -1;
        Survey = null;
        QuestionText = null;
    }


    //getter & setter

    public long getAnswerOptionID() {
        return AnswerOptionID;
    }

    public void setAnswerOptionID(long answerOptionID) {
        AnswerOptionID = answerOptionID;
    }

    public SurveyDAO getSurveyID() {
        return Survey;
    }

    public void setSurveyID(SurveyDAO survey) {
        this.Survey = survey;
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
