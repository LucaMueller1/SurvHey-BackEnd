package DAO;

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
    @Column(name = "Participation_ID")
    private long Participation_ID;

    @Column(name = "Answer")
    private String Answer;

    //Constructors
    public Answer_ChoiceDAO(long participation_ID, String answer) {
        Participation_ID = participation_ID;
        Answer = answer;
    }

    public Answer_ChoiceDAO() {
        Participation_ID = -1;
        Answer = null;
    }



    //getter & setter

    public long getParticipation_ID() {
        return Participation_ID;
    }

    public void setParticipation_ID(long participation_ID) {
        Participation_ID = participation_ID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
