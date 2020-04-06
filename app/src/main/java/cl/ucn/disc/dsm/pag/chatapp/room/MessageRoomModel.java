package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.MessageServiceModel;
import org.threeten.bp.ZonedDateTime;

@Entity(tableName = "messages_table")
public class MessageRoomModel {

  @PrimaryKey
  private int id;

  private int conversationId;
  private int userId;

  @NonNull
  private String content;
  //TODO: Add Date Converter?
  private String createdAt;

  private String latitude;
  private String longitude;
  private String localizationError;

  /**
   * Constructor.
   *
   * @param id                Message id
   * @param conversationId    Conversation id
   * @param userId            User id
   * @param content           The content of the message
   * @param latitude          Latitude component of localization.
   * @param longitude         Longitude component of localization.
   * @param localizationError Error component of localization.
   */
  public MessageRoomModel(int id, int conversationId, int userId, String content, String createdAt,
      String latitude, String longitude, String localizationError) {
    this.id = id;
    this.conversationId = conversationId;
    this.userId = userId;
    this.content = content;
    this.createdAt = createdAt;
    this.latitude = latitude;
    this.longitude = longitude;
    this.localizationError = localizationError;
  }

  @Ignore
  public MessageRoomModel(MessageServiceModel msg) {
    this.id = msg.id;
    this.conversationId = msg.conversationId;
    this.userId = msg.userId;
    this.content = msg.content;
    this.createdAt = msg.createdAt;
    this.latitude = msg.latitude;
    this.longitude = msg.longitude;
    this.localizationError = msg.localizationError;
  }

  public int getId() {
    return id;
  }

  public int getConversationId() {
    return conversationId;
  }

  public int getUserId() {
    return userId;
  }

  public String getContent() {
    return content;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getLocalizationError() {
    return localizationError;
  }
}
