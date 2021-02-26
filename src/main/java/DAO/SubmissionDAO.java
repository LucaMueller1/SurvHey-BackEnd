package DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Table(name = "SurvHey_DB.Submission")
public class SubmissionDAO implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "Submission_ID")
    private long Submission_ID;

    @Column(name = "Participant_ID")
    private long Participant_ID;

    @Column(name = "Survey_ID")
    private long Survey_ID;

    @Column(name = "Submission_Timestamp")
    private Timestamp timestamp;

    @OneToMany(mappedBy = "Submisson")
    private ArrayList<AnswerDAO> answerDAOS;


    //Constructors


    public SubmissionDAO(long submission_ID, long participant_ID, long survey_ID, Timestamp timestamp, ArrayList<AnswerDAO> answerDAOS) {
        Submission_ID = submission_ID;
        Participant_ID = participant_ID;
        Survey_ID = survey_ID;
        this.timestamp = timestamp;
        this.answerDAOS = answerDAOS;
    }

    public SubmissionDAO() {
        Submission_ID = -1;
        Participant_ID = -1;
        Survey_ID = -1;
        this.timestamp = null;
        answerDAOS = null;
    }



    //getter & setter

    public long getSubmission_ID() {
        return Submission_ID;
    }

    public void setSubmission_ID(long submission_ID) {
        Submission_ID = submission_ID;
    }

    public long getParticipant_ID() {
        return Participant_ID;
    }

    public void setParticipant_ID(long participant_ID) {
        Participant_ID = participant_ID;
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

    public ArrayList<AnswerDAO> getAnswers() {
        return answerDAOS;
    }

    public void setAnswers(ArrayList<AnswerDAO> answerDAOS) {
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
