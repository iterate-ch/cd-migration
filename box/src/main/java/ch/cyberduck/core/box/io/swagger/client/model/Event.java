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
 * The description of an event that happened within Box
 */
@Schema(description = "The description of an event that happened within Box")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class Event {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("event_id")
  private String eventId = null;

  @JsonProperty("created_by")
  private AllOfEventCreatedBy createdBy = null;

  @JsonProperty("event_type")
  private AllOfEventEventType eventType = null;

  @JsonProperty("session_id")
  private String sessionId = null;

  @JsonProperty("source")
  private AllOfEventSource source = null;

  @JsonProperty("additional_details")
  private Object additionalDetails = null;

  public Event type(String type) {
    this.type = type;
    return this;
  }

   /**
   * &#x60;event&#x60;
   * @return type
  **/
  @Schema(example = "event", description = "`event`")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Event eventId(String eventId) {
    this.eventId = eventId;
    return this;
  }

   /**
   * The ID of the event object. You can use this to detect duplicate events
   * @return eventId
  **/
  @Schema(example = "f82c3ba03e41f7e8a7608363cc6c0390183c3f83", description = "The ID of the event object. You can use this to detect duplicate events")
  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public Event createdBy(AllOfEventCreatedBy createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * Get createdBy
   * @return createdBy
  **/
  @Schema(description = "")
  public AllOfEventCreatedBy getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(AllOfEventCreatedBy createdBy) {
    this.createdBy = createdBy;
  }

  public Event eventType(AllOfEventEventType eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * Get eventType
   * @return eventType
  **/
  @Schema(description = "")
  public AllOfEventEventType getEventType() {
    return eventType;
  }

  public void setEventType(AllOfEventEventType eventType) {
    this.eventType = eventType;
  }

  public Event sessionId(String sessionId) {
    this.sessionId = sessionId;
    return this;
  }

   /**
   * The session of the user that performed the action. Not all events will populate this attribute.
   * @return sessionId
  **/
  @Schema(example = "70090280850c8d2a1933c1", description = "The session of the user that performed the action. Not all events will populate this attribute.")
  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Event source(AllOfEventSource source) {
    this.source = source;
    return this;
  }

   /**
   * Get source
   * @return source
  **/
  @Schema(description = "")
  public AllOfEventSource getSource() {
    return source;
  }

  public void setSource(AllOfEventSource source) {
    this.source = source;
  }

  public Event additionalDetails(Object additionalDetails) {
    this.additionalDetails = additionalDetails;
    return this;
  }

   /**
   * This object provides additional information about the event if available.  This can include how a user performed an event as well as additional information to correlate an event to external KeySafe logs. Not all events have an &#x60;additional_details&#x60; object.  This object is only available in the Enterprise Events.
   * @return additionalDetails
  **/
  @Schema(example = "{\"key\":\"value\"}", description = "This object provides additional information about the event if available.  This can include how a user performed an event as well as additional information to correlate an event to external KeySafe logs. Not all events have an `additional_details` object.  This object is only available in the Enterprise Events.")
  public Object getAdditionalDetails() {
    return additionalDetails;
  }

  public void setAdditionalDetails(Object additionalDetails) {
    this.additionalDetails = additionalDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.type, event.type) &&
        Objects.equals(this.eventId, event.eventId) &&
        Objects.equals(this.createdBy, event.createdBy) &&
        Objects.equals(this.eventType, event.eventType) &&
        Objects.equals(this.sessionId, event.sessionId) &&
        Objects.equals(this.source, event.source) &&
        Objects.equals(this.additionalDetails, event.additionalDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, eventId, createdBy, eventType, sessionId, source, additionalDetails);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    additionalDetails: ").append(toIndentedString(additionalDetails)).append("\n");
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
