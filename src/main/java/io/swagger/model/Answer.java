package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class Answer   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("question_id")
  private Long questionId = null;

  /**
   * Gets or Sets mode
   */
  public enum ModeEnum {
    RADIO("radio"),
    
    CHECK("check"),
    
    TEXT("text"),
    
    RATING("rating"),
    
    DROPDOWN("dropdown"),
    
    NPS("nps"),
    
    CONSENT("consent");

    private String value;

    ModeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ModeEnum fromValue(String text) {
      for (ModeEnum b : ModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("mode")
  private ModeEnum mode = null;

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

  public Answer mode(ModeEnum mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Get mode
   * @return mode
   **/
  @Schema(description = "")
  
    public ModeEnum getMode() {
    return mode;
  }

  public void setMode(ModeEnum mode) {
    this.mode = mode;
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
  @Schema(example = "[\"Tesla\",\"Hyundai\",\"Porsche\"]", required = true, description = "")
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
        Objects.equals(this.mode, answer.mode) &&
        Objects.equals(this.choices, answer.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId, mode, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Answer {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
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
