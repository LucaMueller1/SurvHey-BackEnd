package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.AnswerOption;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-28T16:38:44.030Z[GMT]")


public class Submission   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("ipAddress")
  private String ipAddress = null;

  @JsonProperty("surveyId")
  private Long surveyId = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @JsonProperty("choices")
  @Valid
  private List<AnswerOption> choices = new ArrayList<AnswerOption>();

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

  public Submission ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  /**
   * Get ipAddress
   * @return ipAddress
   **/
  @Schema(description = "")
  
    public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
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
        Objects.equals(this.ipAddress, submission.ipAddress) &&
        Objects.equals(this.surveyId, submission.surveyId) &&
        Objects.equals(this.timestamp, submission.timestamp) &&
        Objects.equals(this.choices, submission.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ipAddress, surveyId, timestamp, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Submission {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
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
