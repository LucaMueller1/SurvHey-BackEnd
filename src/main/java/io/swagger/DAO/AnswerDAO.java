package io.swagger.DAO;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "SurvHey_DB.Survey_Answer")
public class AnswerDAO implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "Participation_ID")
    private long Participation_ID;

    @Column(name = "Submission_ID")
    private long Submission_ID;

    @Column(name = "Question_ID")
    private long Question_ID;

    @OneToMany(mappedBy = "Survey_Answer")
    private List<Answer_ChoiceDAO> ChoiceList;

    //Constructors
    public AnswerDAO(long participation_ID, long submission_ID, long question_ID, ArrayList<Answer_ChoiceDAO> choiceList) {
        Participation_ID = participation_ID;
        Submission_ID = submission_ID;
        Question_ID = question_ID;
        ChoiceList = choiceList;
    }

    public AnswerDAO() {
        Participation_ID = -1;
        Submission_ID = -1;
        Question_ID = -1;
        ChoiceList = null;
    }

    //getter & setter


    public long getSubmission_ID() {
        return Submission_ID;
    }

    public void setSubmission_ID(long submission_ID) {
        Submission_ID = submission_ID;
    }

    public long getQuestion_ID() {
        return Question_ID;
    }

    public void setQuestion_ID(long question_ID) {
        Question_ID = question_ID;
    }

    public List<Answer_ChoiceDAO> getChoiceList() {
        return ChoiceList;
    }

    public void setChoiceList(ArrayList<Answer_ChoiceDAO> choiceList) {
        ChoiceList = choiceList;
    }
    public long getParticipation_ID() {
        return Participation_ID;
    }

    public void setParticipation_ID(long participation_ID) {
        Participation_ID = participation_ID;
    }


    //input & output streams

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

        inputStream.defaultReadObject();
    }


    private void writeObject(ObjectOutputStream outputStream) throws IOException {

        outputStream.defaultWriteObject();
    }
}