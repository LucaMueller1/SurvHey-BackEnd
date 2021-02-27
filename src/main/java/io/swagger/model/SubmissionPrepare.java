package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SubmissionPrepare
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class SubmissionPrepare   {
  @JsonProperty("survey_id")
  private Long surveyId = null;

  @JsonProperty("answers")
  @Valid
  private List<Answer> answers = new ArrayList<Answer>();

  public SubmissionPrepare surveyId(Long surveyId) {
    this.surveyId = surveyId;
    return this;
  }

  /**
   * Get surveyId
   * @return surveyId
   **/
  @Schema(description = "")
  
    public Long getSurveyId() {
    return surveyId;
  }

  public void setSurveyId(Long surveyId) {
    this.surveyId = surveyId;
  }

  public SubmissionPrepare answers(List<Answer> answers) {
    this.answers = answers;
    return this;
  }

  public SubmissionPrepare addAnswersItem(Answer answersItem) {
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
    SubmissionPrepare submissionPrepare = (SubmissionPrepare) o;
    return Objects.equals(this.surveyId, submissionPrepare.surveyId) &&
        Objects.equals(this.answers, submissionPrepare.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(surveyId, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmissionPrepare {\n");
    
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
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
