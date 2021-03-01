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
 * Analysis
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")


public class Analysis   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("surveyId")
  private Long surveyId = null;

  @JsonProperty("amount")
  private Long amount = null;

  @JsonProperty("countries")
  @Valid
  private Map<String, Integer> countries = null;

  public Analysis id(Long id) {
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

  public Analysis surveyId(Long surveyId) {
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

  public Analysis amount(Long amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "527", description = "")
  
    public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Analysis countries(Map<String, Integer> countries) {
    this.countries = countries;
    return this;
  }

  public Analysis putCountriesItem(String key, Integer countriesItem) {
    if (this.countries == null) {
      this.countries = new HashMap<String, Integer>();
    }
    this.countries.put(key, countriesItem);
    return this;
  }

  /**
   * Get countries
   * @return countries
   **/
  @Schema(example = "{\"Germany\":69,\"USA\":420,\"Spain\":11,\"Norway\":27}", description = "")
  
    public Map<String, Integer> getCountries() {
    return countries;
  }

  public void setCountries(Map<String, Integer> countries) {
    this.countries = countries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Analysis analysis = (Analysis) o;
    return Objects.equals(this.id, analysis.id) &&
        Objects.equals(this.surveyId, analysis.surveyId) &&
        Objects.equals(this.amount, analysis.amount) &&
        Objects.equals(this.countries, analysis.countries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, surveyId, amount, countries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Analysis {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    surveyId: ").append(toIndentedString(surveyId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    countries: ").append(toIndentedString(countries)).append("\n");
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
