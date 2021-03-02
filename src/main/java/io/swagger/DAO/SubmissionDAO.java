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
    private long SubmissionID;


    @Column(name = "Survey_ID")
    private long SurveyID;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Column (name="IP_Adress")
    private String IPadress;

    @OneToMany
    private List<Answer_ChoiceDAO> answerDAOS;


    //Constructors


    public SubmissionDAO(long submissionID, long surveyID, Timestamp timestamp, String IPadress, List<Answer_ChoiceDAO> answerDAOS) {
        SubmissionID = submissionID;
        SurveyID = surveyID;
        this.timestamp = timestamp;
        this.IPadress = IPadress;
        this.answerDAOS = answerDAOS;
    }

    public SubmissionDAO() {
        SubmissionID = -1;
        SurveyID = -1;
        this.timestamp = null;
        this.IPadress = null;
        this.answerDAOS = null;
    }

    //getter & setter

    public long getSubmissionID() {
        return SubmissionID;
    }

    public void setSubmissionID(long submissionID) {
        SubmissionID = submissionID;
    }

    public long getSurveyID() {
        return SurveyID;
    }

    public void setSurveyID(long surveyID) {
        SurveyID = surveyID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getIPadress() {
        return IPadress;
    }

    public void setIPadress(String IPadress) {
        this.IPadress = IPadress;
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
