/*
 * DRACOON
 * REST Web Services for DRACOON<br>Version: 4.20.1  - built at: 2020-04-05 23:00:17<br><br><a title='Developer Information' href='https://developer.dracoon.com'>Developer Information</a>&emsp;&emsp;<a title='Get SDKs on GitHub' href='https://github.com/dracoon'>Get SDKs on GitHub</a>
 *
 * OpenAPI spec version: 4.20.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Notification configuration information
 */
@ApiModel(description = "Notification configuration information")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class NotificationConfig {
  @JsonProperty("channelIds")
  private List<Integer> channelIds = new ArrayList<>();

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("scopeId")
  private Integer scopeId = null;

  @JsonProperty("eventTypeName")
  private String eventTypeName = null;

  public NotificationConfig channelIds(List<Integer> channelIds) {
    this.channelIds = channelIds;
    return this;
  }

  public NotificationConfig addChannelIdsItem(Integer channelIdsItem) {
    this.channelIds.add(channelIdsItem);
    return this;
  }

   /**
   * List of notification channel IDs
   * @return channelIds
  **/
  @ApiModelProperty(required = true, value = "List of notification channel IDs")
  public List<Integer> getChannelIds() {
    return channelIds;
  }

  public void setChannelIds(List<Integer> channelIds) {
    this.channelIds = channelIds;
  }

  public NotificationConfig id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Notification configuration ID
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Notification configuration ID")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public NotificationConfig scopeId(Integer scopeId) {
    this.scopeId = scopeId;
    return this;
  }

   /**
   * Scope ID
   * @return scopeId
  **/
  @ApiModelProperty(required = true, value = "Scope ID")
  public Integer getScopeId() {
    return scopeId;
  }

  public void setScopeId(Integer scopeId) {
    this.scopeId = scopeId;
  }

  public NotificationConfig eventTypeName(String eventTypeName) {
    this.eventTypeName = eventTypeName;
    return this;
  }

   /**
   * Event type name
   * @return eventTypeName
  **/
  @ApiModelProperty(required = true, value = "Event type name")
  public String getEventTypeName() {
    return eventTypeName;
  }

  public void setEventTypeName(String eventTypeName) {
    this.eventTypeName = eventTypeName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotificationConfig notificationConfig = (NotificationConfig) o;
    return Objects.equals(this.channelIds, notificationConfig.channelIds) &&
        Objects.equals(this.id, notificationConfig.id) &&
        Objects.equals(this.scopeId, notificationConfig.scopeId) &&
        Objects.equals(this.eventTypeName, notificationConfig.eventTypeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(channelIds, id, scopeId, eventTypeName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotificationConfig {\n");
    
    sb.append("    channelIds: ").append(toIndentedString(channelIds)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    scopeId: ").append(toIndentedString(scopeId)).append("\n");
    sb.append("    eventTypeName: ").append(toIndentedString(eventTypeName)).append("\n");
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

