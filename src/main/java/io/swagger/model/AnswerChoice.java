package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;


@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")

@Entity
@Table(name = "SurvHey_DB.Question_Answer")
public class AnswerChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Question_AnswerID")
    @JsonProperty("id")
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "Submission_ID", referencedColumnName = "Submission_ID")
    @JsonBackReference
    @JsonProperty("submission")
    private Submission submission = null;

    @Column(name = "ANSWER")
    @JoinColumn(name = "ANSWER_OPTION_ID", referencedColumnName = "ANSWER_OPTION_ID")
    @JsonProperty("ANSWER")
    private AnswerOption Answer = null;

    public AnswerChoice(Long questionAnswerID, Submission submissionID, AnswerOption answer) {
        id = questionAnswerID;
        this.submission = submissionID;
        Answer = answer;
    }

    public AnswerChoice() {
        id = null;
        this.submission = null;
        Answer = null;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long questionAnswerID) {
        id = questionAnswerID;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public AnswerOption getAnswer() {
        return Answer;
    }

    public void setAnswer(AnswerOption answer) {
        Answer = answer;
    }
}

