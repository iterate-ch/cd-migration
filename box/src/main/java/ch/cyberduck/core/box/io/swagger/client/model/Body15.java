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
import ch.cyberduck.core.box.io.swagger.client.model.FoldersfolderIdParent;
import ch.cyberduck.core.box.io.swagger.client.model.Reference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
/**
 * Body15
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class Body15 {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Specifies whether a folder should be synced to a user&#x27;s device or not. This is used by Box Sync (discontinued) and is not used by Box Drive.
   */
  public enum SyncStateEnum {
    SYNCED("synced"),
    NOT_SYNCED("not_synced"),
    PARTIALLY_SYNCED("partially_synced");

    private String value;

    SyncStateEnum(String value) {
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
    public static SyncStateEnum fromValue(String text) {
      for (SyncStateEnum b : SyncStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("sync_state")
  private SyncStateEnum syncState = null;

  @JsonProperty("can_non_owners_invite")
  private Boolean canNonOwnersInvite = null;

  @JsonProperty("parent")
  private FoldersfolderIdParent parent = null;

  @JsonProperty("shared_link")
  private AllOfbody15SharedLink sharedLink = null;

  @JsonProperty("folder_upload_email")
  private AllOfbody15FolderUploadEmail folderUploadEmail = null;

  @JsonProperty("tags")
  private List<String> tags = null;

  @JsonProperty("is_collaboration_restricted_to_enterprise")
  private Boolean isCollaborationRestrictedToEnterprise = null;

  @JsonProperty("collections")
  private List<Reference> collections = null;

  @JsonProperty("can_non_owners_view_collaborators")
  private Boolean canNonOwnersViewCollaborators = null;

  public Body15 name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The optional new name for this folder.
   * @return name
  **/
  @Schema(example = "New Folder", description = "The optional new name for this folder.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Body15 description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The optional description of this folder
   * @return description
  **/
  @Schema(example = "Legal contracts for the new ACME deal", description = "The optional description of this folder")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Body15 syncState(SyncStateEnum syncState) {
    this.syncState = syncState;
    return this;
  }

   /**
   * Specifies whether a folder should be synced to a user&#x27;s device or not. This is used by Box Sync (discontinued) and is not used by Box Drive.
   * @return syncState
  **/
  @Schema(example = "synced", description = "Specifies whether a folder should be synced to a user's device or not. This is used by Box Sync (discontinued) and is not used by Box Drive.")
  public SyncStateEnum getSyncState() {
    return syncState;
  }

  public void setSyncState(SyncStateEnum syncState) {
    this.syncState = syncState;
  }

  public Body15 canNonOwnersInvite(Boolean canNonOwnersInvite) {
    this.canNonOwnersInvite = canNonOwnersInvite;
    return this;
  }

   /**
   * Specifies if users who are not the owner of the folder can invite new collaborators to the folder.
   * @return canNonOwnersInvite
  **/
  @Schema(example = "true", description = "Specifies if users who are not the owner of the folder can invite new collaborators to the folder.")
  public Boolean isCanNonOwnersInvite() {
    return canNonOwnersInvite;
  }

  public void setCanNonOwnersInvite(Boolean canNonOwnersInvite) {
    this.canNonOwnersInvite = canNonOwnersInvite;
  }

  public Body15 parent(FoldersfolderIdParent parent) {
    this.parent = parent;
    return this;
  }

   /**
   * Get parent
   * @return parent
  **/
  @Schema(description = "")
  public FoldersfolderIdParent getParent() {
    return parent;
  }

  public void setParent(FoldersfolderIdParent parent) {
    this.parent = parent;
  }

  public Body15 sharedLink(AllOfbody15SharedLink sharedLink) {
    this.sharedLink = sharedLink;
    return this;
  }

   /**
   * Get sharedLink
   * @return sharedLink
  **/
  @Schema(description = "")
  public AllOfbody15SharedLink getSharedLink() {
    return sharedLink;
  }

  public void setSharedLink(AllOfbody15SharedLink sharedLink) {
    this.sharedLink = sharedLink;
  }

  public Body15 folderUploadEmail(AllOfbody15FolderUploadEmail folderUploadEmail) {
    this.folderUploadEmail = folderUploadEmail;
    return this;
  }

   /**
   * Get folderUploadEmail
   * @return folderUploadEmail
  **/
  @Schema(description = "")
  public AllOfbody15FolderUploadEmail getFolderUploadEmail() {
    return folderUploadEmail;
  }

  public void setFolderUploadEmail(AllOfbody15FolderUploadEmail folderUploadEmail) {
    this.folderUploadEmail = folderUploadEmail;
  }

  public Body15 tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public Body15 addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * The tags for this item. These tags are shown in the Box web app and mobile apps next to an item.  To add or remove a tag, retrieve the item&#x27;s current tags, modify them, and then update this field.  There is a limit of 100 tags per item, and 10,000 unique tags per enterprise.
   * @return tags
  **/
  @Schema(example = "[\"approved\"]", description = "The tags for this item. These tags are shown in the Box web app and mobile apps next to an item.  To add or remove a tag, retrieve the item's current tags, modify them, and then update this field.  There is a limit of 100 tags per item, and 10,000 unique tags per enterprise.")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Body15 isCollaborationRestrictedToEnterprise(Boolean isCollaborationRestrictedToEnterprise) {
    this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
    return this;
  }

   /**
   * Specifies if new invites to this folder are restricted to users within the enterprise. This does not affect existing collaborations.
   * @return isCollaborationRestrictedToEnterprise
  **/
  @Schema(example = "true", description = "Specifies if new invites to this folder are restricted to users within the enterprise. This does not affect existing collaborations.")
  public Boolean isIsCollaborationRestrictedToEnterprise() {
    return isCollaborationRestrictedToEnterprise;
  }

  public void setIsCollaborationRestrictedToEnterprise(Boolean isCollaborationRestrictedToEnterprise) {
    this.isCollaborationRestrictedToEnterprise = isCollaborationRestrictedToEnterprise;
  }

  public Body15 collections(List<Reference> collections) {
    this.collections = collections;
    return this;
  }

  public Body15 addCollectionsItem(Reference collectionsItem) {
    if (this.collections == null) {
      this.collections = new ArrayList<>();
    }
    this.collections.add(collectionsItem);
    return this;
  }

   /**
   * An array of collections to make this folder a member of. Currently we only support the &#x60;favorites&#x60; collection.  To get the ID for a collection, use the [List all collections][1] endpoint.  Passing an empty array &#x60;[]&#x60; or &#x60;null&#x60; will remove the folder from all collections.  [1]: ../advanced-files-and-folders/#get-collections
   * @return collections
  **/
  @Schema(description = "An array of collections to make this folder a member of. Currently we only support the `favorites` collection.  To get the ID for a collection, use the [List all collections][1] endpoint.  Passing an empty array `[]` or `null` will remove the folder from all collections.  [1]: ../advanced-files-and-folders/#get-collections")
  public List<Reference> getCollections() {
    return collections;
  }

  public void setCollections(List<Reference> collections) {
    this.collections = collections;
  }

  public Body15 canNonOwnersViewCollaborators(Boolean canNonOwnersViewCollaborators) {
    this.canNonOwnersViewCollaborators = canNonOwnersViewCollaborators;
    return this;
  }

   /**
   * Restricts collaborators who are not the owner of this folder from viewing other collaborations on this folder.  It also restricts non-owners from inviting new collaborators.  When setting this field to &#x60;false&#x60;, it is required to also set &#x60;can_non_owners_invite_collaborators&#x60; to &#x60;false&#x60; if it has not already been set.
   * @return canNonOwnersViewCollaborators
  **/
  @Schema(example = "true", description = "Restricts collaborators who are not the owner of this folder from viewing other collaborations on this folder.  It also restricts non-owners from inviting new collaborators.  When setting this field to `false`, it is required to also set `can_non_owners_invite_collaborators` to `false` if it has not already been set.")
  public Boolean isCanNonOwnersViewCollaborators() {
    return canNonOwnersViewCollaborators;
  }

  public void setCanNonOwnersViewCollaborators(Boolean canNonOwnersViewCollaborators) {
    this.canNonOwnersViewCollaborators = canNonOwnersViewCollaborators;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body15 body15 = (Body15) o;
    return Objects.equals(this.name, body15.name) &&
        Objects.equals(this.description, body15.description) &&
        Objects.equals(this.syncState, body15.syncState) &&
        Objects.equals(this.canNonOwnersInvite, body15.canNonOwnersInvite) &&
        Objects.equals(this.parent, body15.parent) &&
        Objects.equals(this.sharedLink, body15.sharedLink) &&
        Objects.equals(this.folderUploadEmail, body15.folderUploadEmail) &&
        Objects.equals(this.tags, body15.tags) &&
        Objects.equals(this.isCollaborationRestrictedToEnterprise, body15.isCollaborationRestrictedToEnterprise) &&
        Objects.equals(this.collections, body15.collections) &&
        Objects.equals(this.canNonOwnersViewCollaborators, body15.canNonOwnersViewCollaborators);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, syncState, canNonOwnersInvite, parent, sharedLink, folderUploadEmail, tags, isCollaborationRestrictedToEnterprise, collections, canNonOwnersViewCollaborators);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body15 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    syncState: ").append(toIndentedString(syncState)).append("\n");
    sb.append("    canNonOwnersInvite: ").append(toIndentedString(canNonOwnersInvite)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    sharedLink: ").append(toIndentedString(sharedLink)).append("\n");
    sb.append("    folderUploadEmail: ").append(toIndentedString(folderUploadEmail)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    isCollaborationRestrictedToEnterprise: ").append(toIndentedString(isCollaborationRestrictedToEnterprise)).append("\n");
    sb.append("    collections: ").append(toIndentedString(collections)).append("\n");
    sb.append("    canNonOwnersViewCollaborators: ").append(toIndentedString(canNonOwnersViewCollaborators)).append("\n");
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
