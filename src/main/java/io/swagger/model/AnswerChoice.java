package io.swagger.model;

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
    private long QuestionAnswerID=0;

    @ManyToOne
    @Column(name = "SUBMISSION_ID")
    @JoinColumn(name = "SUBMISSION_ID", referencedColumnName = "SUBMISSION_ID")
    @JsonProperty("subID")
    private Submission submission = null;


    @Column(name = "ANSWER")
    @JoinColumn(name = "ANSWER_OPTION_ID", referencedColumnName = "ANSWER_OPTION_ID")
    @JsonProperty("ANSWER")
    private AnswerOption Answer = null;

    public AnswerChoice(long questionAnswerID, Submission submissionID, AnswerOption answer) {
        QuestionAnswerID = questionAnswerID;
        this.submission = submissionID;
        Answer = answer;
    }

    public AnswerChoice() {
        QuestionAnswerID = 0;
        this.submission = null;
        Answer = null;
    }

    public Long getQuestionAnswerID() {
        return QuestionAnswerID;
    }

    public void setQuestionAnswerID(Long questionAnswerID) {
        QuestionAnswerID = questionAnswerID;
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
