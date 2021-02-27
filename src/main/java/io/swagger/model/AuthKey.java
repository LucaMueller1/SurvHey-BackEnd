package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthKey
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-27T16:32:59.103Z[GMT]")


public class AuthKey   {
  @JsonProperty("user")
  private User user = null;

  @JsonProperty("authKey")
  private String authKey = null;

  @JsonProperty("expiry")
  private OffsetDateTime expiry = null;

  public AuthKey user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   **/
  @Schema(description = "")
  
    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public AuthKey authKey(String authKey) {
    this.authKey = authKey;
    return this;
  }

  /**
   * Get authKey
   * @return authKey
   **/
  @Schema(example = "bWFnZ2llOnN1bW1lcXMgs29E", description = "")
  
    public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }

  public AuthKey expiry(OffsetDateTime expiry) {
    this.expiry = expiry;
    return this;
  }

  /**
   * Get expiry
   * @return expiry
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getExpiry() {
    return expiry;
  }

  public void setExpiry(OffsetDateTime expiry) {
    this.expiry = expiry;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthKey authKey = (AuthKey) o;
    return Objects.equals(this.user, authKey.user) &&
        Objects.equals(this.authKey, authKey.authKey) &&
        Objects.equals(this.expiry, authKey.expiry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, authKey, expiry);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthKey {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    authKey: ").append(toIndentedString(authKey)).append("\n");
    sb.append("    expiry: ").append(toIndentedString(expiry)).append("\n");
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
