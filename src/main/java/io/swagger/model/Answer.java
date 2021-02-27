package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Answer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T14:36:57.683Z[GMT]")


public class Answer   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("question_id")
  private Long questionId = null;

  @JsonProperty("choices")
  @Valid
  private List<OneOfAnswerChoicesItems> choices = new ArrayList<OneOfAnswerChoicesItems>();

  public Answer id(Long id) {
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

  public Answer questionId(Long questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * Get questionId
   * @return questionId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public Answer choices(List<OneOfAnswerChoicesItems> choices) {
    this.choices = choices;
    return this;
  }

  public Answer addChoicesItem(OneOfAnswerChoicesItems choicesItem) {
    this.choices.add(choicesItem);
    return this;
  }

  /**
   * Get choices
   * @return choices
   **/
  @Schema(required = true, description = "")
      @NotNull

    public List<OneOfAnswerChoicesItems> getChoices() {
    return choices;
  }

  public void setChoices(List<OneOfAnswerChoicesItems> choices) {
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
    Answer answer = (Answer) o;
    return Objects.equals(this.id, answer.id) &&
        Objects.equals(this.questionId, answer.questionId) &&
        Objects.equals(this.choices, answer.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Answer {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
