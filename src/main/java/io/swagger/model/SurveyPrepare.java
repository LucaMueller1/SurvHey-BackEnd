package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.AnswerOption;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SurveyPrepare
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-28T16:38:44.030Z[GMT]")


public class SurveyPrepare   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("questionText")
  private String questionText = null;

  /**
   * Gets or Sets mode
   */
  public enum ModeEnum {
    RADIO("radio"),
    
    CHECK("check"),
    
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

  @JsonProperty("answerOptions")
  @Valid
  private List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();

  public SurveyPrepare name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Survey about electric cars", required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SurveyPrepare questionText(String questionText) {
    this.questionText = questionText;
    return this;
  }

  /**
   * Get questionText
   * @return questionText
   **/
  @Schema(example = "What brand of electric car would you buy?", required = true, description = "")
      @NotNull

    public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public SurveyPrepare mode(ModeEnum mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Get mode
   * @return mode
   **/
  @Schema(required = true, description = "")
      @NotNull

    public ModeEnum getMode() {
    return mode;
  }

  public void setMode(ModeEnum mode) {
    this.mode = mode;
  }

  public SurveyPrepare answerOptions(List<AnswerOption> answerOptions) {
    this.answerOptions = answerOptions;
    return this;
  }

  public SurveyPrepare addAnswerOptionsItem(AnswerOption answerOptionsItem) {
    this.answerOptions.add(answerOptionsItem);
    return this;
  }

  /**
   * Get answerOptions
   * @return answerOptions
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<AnswerOption> getAnswerOptions() {
    return answerOptions;
  }

  public void setAnswerOptions(List<AnswerOption> answerOptions) {
    this.answerOptions = answerOptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SurveyPrepare surveyPrepare = (SurveyPrepare) o;
    return Objects.equals(this.name, surveyPrepare.name) &&
        Objects.equals(this.questionText, surveyPrepare.questionText) &&
        Objects.equals(this.mode, surveyPrepare.mode) &&
        Objects.equals(this.answerOptions, surveyPrepare.answerOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, questionText, mode, answerOptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SurveyPrepare {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    questionText: ").append(toIndentedString(questionText)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    answerOptions: ").append(toIndentedString(answerOptions)).append("\n");
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
