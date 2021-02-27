package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Answer;
import io.swagger.model.Participant;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Submission
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class Submission   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("participant")
  private Participant participant = null;

  @JsonProperty("survey_id")
  private Long surveyId = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @JsonProperty("answers")
  @Valid
  private List<Answer> answers = new ArrayList<Answer>();

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

  public Submission participant(Participant participant) {
    this.participant = participant;
    return this;
  }

  /**
   * Get participant
   * @return participant
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }

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

  public Submission answers(List<Answer> answers) {
    this.answers = answers;
    return this;
  }

  public Submission addAnswersItem(Answer answersItem) {
    this.answers.add(answersItem);
    return this;
  }

  /**
   * Get answers
   * @return answers
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
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
        Objects.equals(this.answers, submission.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, participant, surveyId, timestamp, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Submission {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    participant: ").append(toIndentedString(participant)).append("\n");
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
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
