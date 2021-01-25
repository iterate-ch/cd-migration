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
import ch.cyberduck.core.box.io.swagger.client.model.FilesfileIdaddSharedLinkSharedLink;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Body38
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class Body38 {
  @JsonProperty("shared_link")
  private FilesfileIdaddSharedLinkSharedLink sharedLink = null;

  public Body38 sharedLink(FilesfileIdaddSharedLinkSharedLink sharedLink) {
    this.sharedLink = sharedLink;
    return this;
  }

   /**
   * Get sharedLink
   * @return sharedLink
  **/
  @Schema(description = "")
  public FilesfileIdaddSharedLinkSharedLink getSharedLink() {
    return sharedLink;
  }

  public void setSharedLink(FilesfileIdaddSharedLinkSharedLink sharedLink) {
    this.sharedLink = sharedLink;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body38 body38 = (Body38) o;
    return Objects.equals(this.sharedLink, body38.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sharedLink);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body38 {\n");
    
    sb.append("    sharedLink: ").append(toIndentedString(sharedLink)).append("\n");
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
