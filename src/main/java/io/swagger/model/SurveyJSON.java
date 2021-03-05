package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Survey
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")

public class SurveyJSON {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("questionText")
  private String questionText = null;

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("user")
  private User user = null;

  @JsonProperty("answerOptions")
  @Valid
  private List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();


  public SurveyJSON(Long id, String name, String questionText, String mode, User user, @Valid List<AnswerOption> answerOptions) {
    this.id = id;
    this.name = name;
    this.questionText = questionText;
    this.mode = mode;
    this.user = user;
    this.answerOptions = answerOptions;
  }

  public SurveyJSON() {

  }


  public SurveyJSON id(Long id) {
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

  public SurveyJSON name(String name) {
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

  public SurveyJSON questionText(String questionText) {
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

  public SurveyJSON mode(String mode) {
    this.mode = mode;
    return this;
  }

  /**
   * Get mode
   * @return mode
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public SurveyJSON user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public SurveyJSON answerOptions(List<AnswerOption> answerOptions) {
    this.answerOptions = answerOptions;
    return this;
  }

  public SurveyJSON addAnswerOptionsItem(AnswerOption answerOptionsItem) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SurveyJSON survey = (SurveyJSON) o;
    return Objects.equals(this.id, survey.id) &&
        Objects.equals(this.name, survey.name) &&
        Objects.equals(this.questionText, survey.questionText) &&
        Objects.equals(this.mode, survey.mode) &&
        Objects.equals(this.user, survey.user) &&
        Objects.equals(this.answerOptions, survey.answerOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, questionText, mode, user, answerOptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Survey {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    questionText: ").append(toIndentedString(questionText)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    answerOptions: ").append(toIndentedString(answerOptions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
