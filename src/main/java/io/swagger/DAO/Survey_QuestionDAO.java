package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Table(name="SurvHey_DB.Survey_Question")
public class Survey_QuestionDAO implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "Question_ID")
    private long Question_ID;

    @Column(name = "Survey_Id")
    private long Survey_ID;

    @Column(name = "Question_Text")
    private String Question_Text;

    @Column(name = "Answer_Mode")
    private String Question_Mode;

    @OneToMany(mappedBy = "Survey_Question")
    private ArrayList<Answer_OptionDAO> answer_OptionDAOS;




    //Constructors

    public Survey_QuestionDAO(long question_ID, long survey_ID, String question_Text, String question_Mode, ArrayList<Answer_OptionDAO> answer_OptionDAOS) {
        Question_ID = question_ID;
        Survey_ID = survey_ID;
        Question_Text = question_Text;
        Question_Mode = question_Mode;
        this.answer_OptionDAOS = answer_OptionDAOS;
    }


    public Survey_QuestionDAO() {
        Question_ID = -1;
        Survey_ID = -1;
        Question_Text = null;
        Question_Mode = null;
        answer_OptionDAOS = null;
    }



    //getter & setter

    public long getQuestion_ID() {
        return Question_ID;
    }

    public void setQuestion_ID(long question_ID) {
        Question_ID = question_ID;
    }

    public long getSurvey_ID() {
        return Survey_ID;
    }

    public void setSurvey_ID(long survey_ID) {
        Survey_ID = survey_ID;
    }

    public String getQuestion_Text() {
        return Question_Text;
    }

    public void setQuestion_Text(String question_Text) {
        Question_Text = question_Text;
    }

    public String getQuestion_Mode() {
        return Question_Mode;
    }

    public void setQuestion_Mode(String question_Mode) {
        Question_Mode = question_Mode;
    }

    public ArrayList<Answer_OptionDAO> getAnswer_Options() {
        return answer_OptionDAOS;
    }

    public void setAnswer_Options(ArrayList<Answer_OptionDAO> answer_OptionDAOS) {
        this.answer_OptionDAOS = answer_OptionDAOS;
    }



    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
