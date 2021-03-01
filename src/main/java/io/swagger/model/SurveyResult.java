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
 * SurveyResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")


public class SurveyResult   {
  @JsonProperty("surveyId")
  private Long surveyId = null;

  @JsonProperty("choices")
  @Valid
  private Map<String, Integer> choices = null;

  public SurveyResult surveyId(Long surveyId) {
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

  public SurveyResult choices(Map<String, Integer> choices) {
    this.choices = choices;
    return this;
  }

  public SurveyResult putChoicesItem(String key, Integer choicesItem) {
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
    SurveyResult surveyResult = (SurveyResult) o;
    return Objects.equals(this.surveyId, surveyResult.surveyId) &&
        Objects.equals(this.choices, surveyResult.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(surveyId, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SurveyResult {\n");
    
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
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
