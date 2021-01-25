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
 * A request to refresh an Access Token. Use this API to refresh an expired Access Token using a valid Refresh Token.
 */
@Schema(description = "A request to refresh an Access Token. Use this API to refresh an expired Access Token using a valid Refresh Token.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-01-25T11:35:18.602705+01:00[Europe/Zurich]")
public class PostOAuth2TokenRefreshAccessToken {
  /**
   * The type of request being made, in this case a refresh request.
   */
  public enum GrantTypeEnum {
    TOKEN("refresh_token");

    private String value;

    GrantTypeEnum(String value) {
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
    public static GrantTypeEnum fromValue(String text) {
      for (GrantTypeEnum b : GrantTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

  }  @JsonProperty("grant_type")
  private GrantTypeEnum grantType = null;

  @JsonProperty("client_id")
  private String clientId = null;

  @JsonProperty("client_secret")
  private String clientSecret = null;

  @JsonProperty("refresh_token")
  private String refreshToken = null;

  public PostOAuth2TokenRefreshAccessToken grantType(GrantTypeEnum grantType) {
    this.grantType = grantType;
    return this;
  }

   /**
   * The type of request being made, in this case a refresh request.
   * @return grantType
  **/
  @Schema(example = "refresh_token", required = true, description = "The type of request being made, in this case a refresh request.")
  public GrantTypeEnum getGrantType() {
    return grantType;
  }

  public void setGrantType(GrantTypeEnum grantType) {
    this.grantType = grantType;
  }

  public PostOAuth2TokenRefreshAccessToken clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

   /**
   * The client ID of the application requesting to refresh the token.
   * @return clientId
  **/
  @Schema(example = "ly1nj6n11vionaie65emwzk575hnnmrk", required = true, description = "The client ID of the application requesting to refresh the token.")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public PostOAuth2TokenRefreshAccessToken clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

   /**
   * The client secret of the application requesting to refresh the token.
   * @return clientSecret
  **/
  @Schema(example = "hOzsTeFlT6ko0dme22uGbQal04SBPYc1", required = true, description = "The client secret of the application requesting to refresh the token.")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public PostOAuth2TokenRefreshAccessToken refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

   /**
   * The refresh token to refresh.
   * @return refreshToken
  **/
  @Schema(example = "c3FIOG9vSGV4VHo4QzAyg5T1JvNnJoZ3ExaVNyQWw6WjRsanRKZG5lQk9qUE1BVQ", required = true, description = "The refresh token to refresh.")
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostOAuth2TokenRefreshAccessToken postOAuth2TokenRefreshAccessToken = (PostOAuth2TokenRefreshAccessToken) o;
    return Objects.equals(this.grantType, postOAuth2TokenRefreshAccessToken.grantType) &&
        Objects.equals(this.clientId, postOAuth2TokenRefreshAccessToken.clientId) &&
        Objects.equals(this.clientSecret, postOAuth2TokenRefreshAccessToken.clientSecret) &&
        Objects.equals(this.refreshToken, postOAuth2TokenRefreshAccessToken.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grantType, clientId, clientSecret, refreshToken);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostOAuth2TokenRefreshAccessToken {\n");
    
    sb.append("    grantType: ").append(toIndentedString(grantType)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
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
