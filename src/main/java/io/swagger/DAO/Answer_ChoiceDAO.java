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
    private long SubmissionID;

    @Id
    @Column(name = "Answer_Option_ID")
    private long AnswerOptionID;

    //Constructors


    public Answer_ChoiceDAO(long submissionID, long answerOptionID) {
        SubmissionID = submissionID;
        AnswerOptionID = answerOptionID;
    }

    public Answer_ChoiceDAO() {
        SubmissionID = -1;
        AnswerOptionID = -1;
    }



    //getter & setter

    public long getSubmissionID() {
        return SubmissionID;
    }

    public void setSubmissionID(long submissionID) {
        SubmissionID = submissionID;
    }

    public long getAnswerOptionID() {
        return AnswerOptionID;
    }

    public void setAnswerOptionID(long answerOptionID) {
        AnswerOptionID = answerOptionID;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}
