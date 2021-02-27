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
 * Results
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T14:36:57.683Z[GMT]")


public class Results   {
  @JsonProperty("survey_id")
  private Long surveyId = null;

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
  private List<OneOfResultsChoicesItems> choices = null;

  public Results surveyId(Long surveyId) {
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

  public Results mode(ModeEnum mode) {
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

  public Results choices(List<OneOfResultsChoicesItems> choices) {
    this.choices = choices;
    return this;
  }

  public Results addChoicesItem(OneOfResultsChoicesItems choicesItem) {
    if (this.choices == null) {
      this.choices = new ArrayList<OneOfResultsChoicesItems>();
    }
    this.choices.add(choicesItem);
    return this;
  }

  /**
   * Get choices
   * @return choices
   **/
  @Schema(description = "")
  
    public List<OneOfResultsChoicesItems> getChoices() {
    return choices;
  }

  public void setChoices(List<OneOfResultsChoicesItems> choices) {
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
    Results results = (Results) o;
    return Objects.equals(this.surveyId, results.surveyId) &&
        Objects.equals(this.mode, results.mode) &&
        Objects.equals(this.choices, results.choices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(surveyId, mode, choices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Results {\n");
    
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
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
