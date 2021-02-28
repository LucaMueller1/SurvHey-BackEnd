package io.swagger.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Entity
@Table(name = "SurvHey_DB.Question_Answer")
public class Answer_ChoiceDAO implements Serializable {
    @Id
    @Column(name = "Submission_ID")
    private long Submission_ID;

    @Id
    @Column(name = "Answer_Option_ID")
    private long Answer_Option_ID;

    //Constructors
    public Answer_ChoiceDAO(long participation_ID, long Answer_Option_ID) {
        this.Submission_ID = participation_ID;
        this.Answer_Option_ID = Answer_Option_ID;
    }

    public Answer_ChoiceDAO() {
        Submission_ID = -1;
        Answer_Option_ID = -1;
    }



    //getter & setter

    public long getParticipation_ID() {
        return Submission_ID;
    }

    public void setParticipation_ID(long participation_ID) {
        Submission_ID = participation_ID;
    }

    public long getAnswer() {
        return Answer_Option_ID;
    }

    public void setAnswer(long answer) {
        Answer_Option_ID = answer;
    }

    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
