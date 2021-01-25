/*
 * Box Platform API
 * [Box Platform](https://box.dev) provides functionality to provide access to content stored within [Box](https://box.com). It provides endpoints for basic manipulation of files and folders, management of users within an enterprise, as well as more complex topics such as legal holds and retention policies.
 *
 * OpenAPI spec version: 2.0.0
 * Contact: devrel@box.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.cyberduck.core.box.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Body65
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class Body65 {
  /**
   * Whether this terms of service is active.
   */
  public enum StatusEnum {
    ENABLED("enabled"),
    DISABLED("disabled");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("status")
  private StatusEnum status = null;

  /**
   * The type of user to set the terms of service for.
   */
  public enum TosTypeEnum {
    EXTERNAL("external"),
    MANAGED("managed");

    private String value;

    TosTypeEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static TosTypeEnum fromValue(String text) {
      for (TosTypeEnum b : TosTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("tos_type")
  private TosTypeEnum tosType = null;

  @JsonProperty("text")
  private String text = null;

  public Body65 status(StatusEnum status) {
    this.status = status;
    return this;
  }

   /**
   * Whether this terms of service is active.
   * @return status
  **/
  @Schema(example = "enabled", required = true, description = "Whether this terms of service is active.")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Body65 tosType(TosTypeEnum tosType) {
    this.tosType = tosType;
    return this;
  }

   /**
   * The type of user to set the terms of service for.
   * @return tosType
  **/
  @Schema(example = "managed", description = "The type of user to set the terms of service for.")
  public TosTypeEnum getTosType() {
    return tosType;
  }

  public void setTosType(TosTypeEnum tosType) {
    this.tosType = tosType;
  }

  public Body65 text(String text) {
    this.text = text;
    return this;
  }

   /**
   * The terms of service text to display to users.  The text can be set to empty if the &#x60;status&#x60; is set to &#x60;disabled&#x60;.
   * @return text
  **/
  @Schema(example = "By collaborating on this file you are accepting...", required = true, description = "The terms of service text to display to users.  The text can be set to empty if the `status` is set to `disabled`.")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body65 body65 = (Body65) o;
    return Objects.equals(this.status, body65.status) &&
        Objects.equals(this.tosType, body65.tosType) &&
        Objects.equals(this.text, body65.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, tosType, text);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body65 {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    tosType: ").append(toIndentedString(tosType)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
