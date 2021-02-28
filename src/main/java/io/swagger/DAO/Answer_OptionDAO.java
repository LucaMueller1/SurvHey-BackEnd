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
    private long Answer_Option_ID;

    @Column(name = "Survey_ID")
    private long Survey_ID;

    @Column(name = "Answer_Option")
    private String Question_Text;


    //Constructors
    public Answer_OptionDAO(long answer_Option_ID, long question_Id, String question_Text) {
        Answer_Option_ID = answer_Option_ID;
        Survey_ID = question_Id;
        Question_Text = question_Text;
    }

    public Answer_OptionDAO() {
        Answer_Option_ID = -1;
        Survey_ID = -1;
        Question_Text = null;
    }


    //getter & setter


    public long getAnswer_Option_ID() {
        return Answer_Option_ID;
    }

    public void setAnswer_Option_ID(long answer_Option_ID) {
        Answer_Option_ID = answer_Option_ID;
    }

    public long getQuestion_Id() {
        return Survey_ID;
    }

    public void setQuestion_Id(long question_Id) {
        Survey_ID = question_Id;
    }

    public String getQuestion_Text() {
        return Question_Text;
    }

    public void setQuestion_Text(String question_Text) {
        Question_Text = question_Text;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
