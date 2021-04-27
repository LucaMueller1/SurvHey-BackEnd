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
 * Fingerprint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")


public class Fingerprint   {

    @JsonProperty("submission")
    private Submission submission = null;

    @JsonProperty("cityAndCountry")
    private String cityAndCountry = null;

    public Fingerprint() {

    }


    public Fingerprint(Submission submission, String cityAndCountry) {
        this.submission = submission;
        this.cityAndCountry = cityAndCountry;
    }


    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public String getCityAndCountry() {
        return cityAndCountry;
    }

    public void setCityAndCountry(String cityAndCountry) {
        this.cityAndCountry = cityAndCountry;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fingerprint fingerprint = (Fingerprint) o;
        return Objects.equals(this.submission, fingerprint.submission) &&
                Objects.equals(this.cityAndCountry, fingerprint.cityAndCountry);

    }

    @Override
    public int hashCode() {
        return Objects.hash(submission, cityAndCountry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Fingerprint {\n");
        sb.append("    submission: ").append(toIndentedString(submission)).append("\n");
        sb.append("    country: ").append(toIndentedString(cityAndCountry)).append("\n");
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
