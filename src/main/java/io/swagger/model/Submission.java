package io.swagger.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.AnswerOption;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Submission
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")


@Entity
@Table(name = "SurvHey_DB.Submission")
public class Submission   {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Submission_ID")
  @JsonProperty("id")
  private Long id = null;


  @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @JoinColumn(name = "Participant_ID", referencedColumnName = "Participant_ID")
  @JsonProperty("participant")
  @Valid
  private Participant participant = null;


  @Column(name = "Survey_ID")
  @JsonProperty("surveyId")
  private Long surveyId = null;

  @Column(name = "Timestamp")
  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "SurvHey_DB.Question_Answer",
          joinColumns = { @JoinColumn(name = "Submission_ID") },
          inverseJoinColumns = { @JoinColumn(name = "ANSWER_OPTION_ID") }
  )
  @JsonProperty("choices")
  @Valid
  private List<AnswerOption> choices = new ArrayList<>();

  public Submission(Long id, Long surveyId, OffsetDateTime timestamp, @Valid List<AnswerOption> choices, @Valid Participant participant) {
    this.id = id;
    this.surveyId = surveyId;
    this.timestamp = timestamp;
    this.choices = choices;
    this.participant = participant;
  }

  public Submission() {}

  public Submission id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

 /* public Submission ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }*/

  /**
   * Get ipAddress
   * @return ipAddress
   **/
  @Schema(required = true, description = "")
      @NotNull


  public Submission surveyId(Long surveyId) {
    this.surveyId = surveyId;
    return this;
  }

  /**
   * Get surveyId
   * @return surveyId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Long getSurveyId() {
    return surveyId;
  }

  public void setSurveyId(Long surveyId) {
    this.surveyId = surveyId;
  }

  public Submission timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

/**
 * Get participant
 * @return participant
 **/
@Schema(required = true, description = "")
@NotNull
  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }


  /**
   * Get timestamp
   * @return timestamp
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Submission choices(List<AnswerOption> choices) {
    this.choices = choices;
    return this;
  }

  public Submission addChoicesItem(AnswerOption choicesItem) {
    this.choices.add(choicesItem);
    return this;
  }

  /**
   * Get choices
   * @return choices
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<AnswerOption> getChoices() {
    return choices;
  }

  public void setChoices(List<AnswerOption> choices) {
    this.choices = choices;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Submission submission = (Submission) o;
    return Objects.equals(this.id, submission.id) &&
        Objects.equals(this.participant, submission.participant) &&
        Objects.equals(this.surveyId, submission.surveyId) &&
        Objects.equals(this.timestamp, submission.timestamp) &&
        Objects.equals(this.choices, submission.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, participant, surveyId, timestamp, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Submission {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(participant)).append("\n");
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    choices: ").append(toIndentedString(choices)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
