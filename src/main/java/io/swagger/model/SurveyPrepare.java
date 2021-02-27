package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Question;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class SurveyPrepare   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("questions")
  @Valid
  private List<Question> questions = new ArrayList<Question>();

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

  public SurveyPrepare questions(List<Question> questions) {
    this.questions = questions;
    return this;
  }

  public SurveyPrepare addQuestionsItem(Question questionsItem) {
    this.questions.add(questionsItem);
    return this;
  }

  /**
   * Get questions
   * @return questions
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
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
        Objects.equals(this.questions, surveyPrepare.questions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, questions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SurveyPrepare {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
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
