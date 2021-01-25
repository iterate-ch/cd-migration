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
import ch.cyberduck.core.box.io.swagger.client.model.FolderMini;
import ch.cyberduck.core.box.io.swagger.client.model.WebLinkMini;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
/**
 * Web links are objects that point to URLs. These objects are also known as bookmarks within the Box web application.  Web link objects are treated similarly to file objects, they will also support most actions that apply to regular files.
 */
@Schema(description = "Web links are objects that point to URLs. These objects are also known as bookmarks within the Box web application.  Web link objects are treated similarly to file objects, they will also support most actions that apply to regular files.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class WebLink extends WebLinkMini {
  @JsonProperty("parent")
  private Object parent = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("path_collection")
  private List<FolderMini> pathCollection = null;

  @JsonProperty("created_at")
  private DateTime createdAt = null;

  @JsonProperty("modified_at")
  private DateTime modifiedAt = null;

  @JsonProperty("trashed_at")
  private DateTime trashedAt = null;

  @JsonProperty("purged_at")
  private DateTime purgedAt = null;

  @JsonProperty("created_by")
  private Object createdBy = null;

  @JsonProperty("modified_by")
  private Object modifiedBy = null;

  @JsonProperty("owned_by")
  private Object ownedBy = null;

  @JsonProperty("shared_link")
  private Object sharedLink = null;

  /**
   * Whether this item is deleted or not. Values include &#x60;active&#x60;, &#x60;trashed&#x60; if the file has been moved to the trash, and &#x60;deleted&#x60; if the file has been permanently deleted
   */
  public enum ItemStatusEnum {
    ACTIVE("active"),
    TRASHED("trashed"),
    DELETED("deleted");

    private String value;

    ItemStatusEnum(String value) {
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
    public static ItemStatusEnum fromValue(String text) {
      for (ItemStatusEnum b : ItemStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("item_status")
  private ItemStatusEnum itemStatus = null;

  public WebLink parent(Object parent) {
    this.parent = parent;
    return this;
  }

   /**
   * Get parent
   * @return parent
  **/
  @Schema(description = "")
  public Object getParent() {
    return parent;
  }

  public void setParent(Object parent) {
    this.parent = parent;
  }

  public WebLink description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The description accompanying the web link. This is visible within the Box web application.
   * @return description
  **/
  @Schema(example = "Example page", description = "The description accompanying the web link. This is visible within the Box web application.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WebLink pathCollection(List<FolderMini> pathCollection) {
    this.pathCollection = pathCollection;
    return this;
  }

  public WebLink addPathCollectionItem(FolderMini pathCollectionItem) {
    if (this.pathCollection == null) {
      this.pathCollection = new ArrayList<>();
    }
    this.pathCollection.add(pathCollectionItem);
    return this;
  }

   /**
   * The path of folders to this link, starting at the root
   * @return pathCollection
  **/
  @Schema(description = "The path of folders to this link, starting at the root")
  public List<FolderMini> getPathCollection() {
    return pathCollection;
  }

  public void setPathCollection(List<FolderMini> pathCollection) {
    this.pathCollection = pathCollection;
  }

  public WebLink createdAt(DateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * When this file was created on Box’s servers.
   * @return createdAt
  **/
  @Schema(example = "2012-12-12T10:53:43-08:00", description = "When this file was created on Box’s servers.")
  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public WebLink modifiedAt(DateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
    return this;
  }

   /**
   * When this file was last updated on the Box servers.
   * @return modifiedAt
  **/
  @Schema(example = "2012-12-12T10:53:43-08:00", description = "When this file was last updated on the Box servers.")
  public DateTime getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(DateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public WebLink trashedAt(DateTime trashedAt) {
    this.trashedAt = trashedAt;
    return this;
  }

   /**
   * When this file was last moved to the trash.
   * @return trashedAt
  **/
  @Schema(example = "2012-12-12T10:53:43-08:00", description = "When this file was last moved to the trash.")
  public DateTime getTrashedAt() {
    return trashedAt;
  }

  public void setTrashedAt(DateTime trashedAt) {
    this.trashedAt = trashedAt;
  }

  public WebLink purgedAt(DateTime purgedAt) {
    this.purgedAt = purgedAt;
    return this;
  }

   /**
   * When this file will be permanently deleted.
   * @return purgedAt
  **/
  @Schema(example = "2012-12-12T10:53:43-08:00", description = "When this file will be permanently deleted.")
  public DateTime getPurgedAt() {
    return purgedAt;
  }

  public void setPurgedAt(DateTime purgedAt) {
    this.purgedAt = purgedAt;
  }

  public WebLink createdBy(Object createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * Get createdBy
   * @return createdBy
  **/
  @Schema(description = "")
  public Object getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Object createdBy) {
    this.createdBy = createdBy;
  }

  public WebLink modifiedBy(Object modifiedBy) {
    this.modifiedBy = modifiedBy;
    return this;
  }

   /**
   * Get modifiedBy
   * @return modifiedBy
  **/
  @Schema(description = "")
  public Object getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(Object modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public WebLink ownedBy(Object ownedBy) {
    this.ownedBy = ownedBy;
    return this;
  }

   /**
   * Get ownedBy
   * @return ownedBy
  **/
  @Schema(description = "")
  public Object getOwnedBy() {
    return ownedBy;
  }

  public void setOwnedBy(Object ownedBy) {
    this.ownedBy = ownedBy;
  }

  public WebLink sharedLink(Object sharedLink) {
    this.sharedLink = sharedLink;
    return this;
  }

   /**
   * Get sharedLink
   * @return sharedLink
  **/
  @Schema(description = "")
  public Object getSharedLink() {
    return sharedLink;
  }

  public void setSharedLink(Object sharedLink) {
    this.sharedLink = sharedLink;
  }

  public WebLink itemStatus(ItemStatusEnum itemStatus) {
    this.itemStatus = itemStatus;
    return this;
  }

   /**
   * Whether this item is deleted or not. Values include &#x60;active&#x60;, &#x60;trashed&#x60; if the file has been moved to the trash, and &#x60;deleted&#x60; if the file has been permanently deleted
   * @return itemStatus
  **/
  @Schema(example = "active", description = "Whether this item is deleted or not. Values include `active`, `trashed` if the file has been moved to the trash, and `deleted` if the file has been permanently deleted")
  public ItemStatusEnum getItemStatus() {
    return itemStatus;
  }

  public void setItemStatus(ItemStatusEnum itemStatus) {
    this.itemStatus = itemStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebLink webLink = (WebLink) o;
    return Objects.equals(this.parent, webLink.parent) &&
        Objects.equals(this.description, webLink.description) &&
        Objects.equals(this.pathCollection, webLink.pathCollection) &&
        Objects.equals(this.createdAt, webLink.createdAt) &&
        Objects.equals(this.modifiedAt, webLink.modifiedAt) &&
        Objects.equals(this.trashedAt, webLink.trashedAt) &&
        Objects.equals(this.purgedAt, webLink.purgedAt) &&
        Objects.equals(this.createdBy, webLink.createdBy) &&
        Objects.equals(this.modifiedBy, webLink.modifiedBy) &&
        Objects.equals(this.ownedBy, webLink.ownedBy) &&
        Objects.equals(this.sharedLink, webLink.sharedLink) &&
        Objects.equals(this.itemStatus, webLink.itemStatus) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parent, description, pathCollection, createdAt, modifiedAt, trashedAt, purgedAt, createdBy, modifiedBy, ownedBy, sharedLink, itemStatus, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebLink {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    pathCollection: ").append(toIndentedString(pathCollection)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    modifiedAt: ").append(toIndentedString(modifiedAt)).append("\n");
    sb.append("    trashedAt: ").append(toIndentedString(trashedAt)).append("\n");
    sb.append("    purgedAt: ").append(toIndentedString(purgedAt)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
    sb.append("    ownedBy: ").append(toIndentedString(ownedBy)).append("\n");
    sb.append("    sharedLink: ").append(toIndentedString(sharedLink)).append("\n");
    sb.append("    itemStatus: ").append(toIndentedString(itemStatus)).append("\n");
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
