package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SurvHey_DB.Submission")
public class SubmissionDAO implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "Submission_ID")
    private long Submission_ID;


    @Column(name = "Survey_ID")
    private long Survey_ID;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Column (name="IP_Adress")
    private String IP_Adress;

    @OneToMany
    private List<Answer_ChoiceDAO> answerDAOS;


    //Constructors

    public SubmissionDAO(long submission_ID, long survey_ID, Timestamp timestamp, String IP_Adress, List<Answer_ChoiceDAO> answerDAOS) {
        Submission_ID = submission_ID;
        Survey_ID = survey_ID;
        this.timestamp = timestamp;
        this.IP_Adress = IP_Adress;
        this.answerDAOS = answerDAOS;
    }
    public SubmissionDAO() {
        Submission_ID = -1;
        Survey_ID = -1;
        this.timestamp = null;
        this.IP_Adress = null;
        this.answerDAOS = null;
    }

    //getter & setter

    public long getSubmission_ID() {
        return Submission_ID;
    }

    public void setSubmission_ID(long submission_ID) {
        Submission_ID = submission_ID;
    }

    public long getSurvey_ID() {
        return Survey_ID;
    }

    public void setSurvey_ID(long survey_ID) {
        Survey_ID = survey_ID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getIP_Adress() {
        return IP_Adress;
    }

    public void setIP_Adress(String IP_Adress) {
        this.IP_Adress = IP_Adress;
    }

    public List<Answer_ChoiceDAO> getAnswerDAOS() {
        return answerDAOS;
    }

    public void setAnswerDAOS(List<Answer_ChoiceDAO> answerDAOS) {
        this.answerDAOS = answerDAOS;
    }


//input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }



}
