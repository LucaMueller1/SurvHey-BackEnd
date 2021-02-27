package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QuestionResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class QuestionResult   {
  @JsonProperty("question_id")
  private Long questionId = null;

  @JsonProperty("choices")
  @Valid
  private Map<String, Integer> choices = null;

  public QuestionResult questionId(Long questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * Get questionId
   * @return questionId
   **/
  @Schema(description = "")
  
    public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public QuestionResult choices(Map<String, Integer> choices) {
    this.choices = choices;
    return this;
  }

  public QuestionResult putChoicesItem(String key, Integer choicesItem) {
    if (this.choices == null) {
      this.choices = new HashMap<String, Integer>();
    }
    this.choices.put(key, choicesItem);
    return this;
  }

  /**
   * Get choices
   * @return choices
   **/
  @Schema(example = "{\"Tesla\":0.65,\"Hyundai\":0.15,\"Porsche\":0.2}", description = "")
  
    public Map<String, Integer> getChoices() {
    return choices;
  }

  public void setChoices(Map<String, Integer> choices) {
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
    QuestionResult questionResult = (QuestionResult) o;
    return Objects.equals(this.questionId, questionResult.questionId) &&
        Objects.equals(this.choices, questionResult.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionId, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionResult {\n");
    
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
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
