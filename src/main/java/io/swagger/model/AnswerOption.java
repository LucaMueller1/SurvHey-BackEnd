package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * AnswerOption
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")

@Entity
@Table(name = "SurvHey_DB.Answer_Option")
public class AnswerOption {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Answer_Option_ID")
  @JsonProperty("id")
  private Long id = null;

  @ManyToOne
  @JoinColumn(name = "Survey_ID", referencedColumnName = "Survey_ID")
  @JsonBackReference
  @JsonProperty("surveyId")
  private Survey survey = null;

  @Column(name = "Answer_Option")
  @JsonProperty("content")
  private String content = null;

  public AnswerOption(Long id, Survey survey, String content) {
    this.id = id;
    this.survey = survey;
    this.content = content;
  }

  public AnswerOption() {
    this.id = null;
    this.survey = null;
    this.content = null;
  }

  public AnswerOption id(Long id) {
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

  public AnswerOption surveyId(Survey surveyId) {
    this.survey = surveyId;
    return this;
  }

  /**
   * Get surveyId
   * @return surveyId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Survey getSurvey() {
    return this.survey;
  }

  public void setSurvey(Survey surveyId) {
    this.survey = surveyId;
  }

  public AnswerOption content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  @Schema(example = "Tesla", required = true, description = "")
      @NotNull

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnswerOption answerOption = (AnswerOption) o;
    return Objects.equals(this.id, answerOption.id) &&
        Objects.equals(this.survey, answerOption.survey) &&
        Objects.equals(this.content, answerOption.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, survey, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnswerOption {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    surveyId: ").append(toIndentedString(survey.getId())).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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
