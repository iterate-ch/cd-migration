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
import ch.cyberduck.core.box.io.swagger.client.model.TermsOfServiceUserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
/**
 * A list of terms of service user statuses
 */
@Schema(description = "A list of terms of service user statuses")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class TermsOfServiceUserStatuses {
  @JsonProperty("total_count")
  private Long totalCount = null;

  @JsonProperty("entries")
  private List<TermsOfServiceUserStatus> entries = null;

  public TermsOfServiceUserStatuses totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

   /**
   * The total number of objects.
   * @return totalCount
  **/
  @Schema(example = "2", description = "The total number of objects.")
  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  public TermsOfServiceUserStatuses entries(List<TermsOfServiceUserStatus> entries) {
    this.entries = entries;
    return this;
  }

  public TermsOfServiceUserStatuses addEntriesItem(TermsOfServiceUserStatus entriesItem) {
    if (this.entries == null) {
      this.entries = new ArrayList<>();
    }
    this.entries.add(entriesItem);
    return this;
  }

   /**
   * Get entries
   * @return entries
  **/
  @Schema(description = "")
  public List<TermsOfServiceUserStatus> getEntries() {
    return entries;
  }

  public void setEntries(List<TermsOfServiceUserStatus> entries) {
    this.entries = entries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermsOfServiceUserStatuses termsOfServiceUserStatuses = (TermsOfServiceUserStatuses) o;
    return Objects.equals(this.totalCount, termsOfServiceUserStatuses.totalCount) &&
        Objects.equals(this.entries, termsOfServiceUserStatuses.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TermsOfServiceUserStatuses {\n");
    
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
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
