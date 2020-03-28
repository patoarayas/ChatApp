package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.threeten.bp.ZonedDateTime;

@Entity(tableName = "messages_table")
public class Message {

  @PrimaryKey
  @NonNull
  private int msgId;

  private int conversationId;
  private int userId;
  private String content;
  private ZonedDateTime timestamp;

  private String latitude;
  private String longitude;
  private String localizationError;

  /**
   * Constructor.
   * @param msgId Message id
   * @param conversationId  Conversation id
   * @param userId User id
   * @param content The content of the message
   * @param latitude Latitude component of localization.
   * @param longitude Longitude component of localization.
   * @param error Error component of localization.
   */
  public Message(int msgId, int conversationId, int userId, String content,
      String latitude, String longitude, String error) {
    this.msgId = msgId;
    this.conversationId = conversationId;
    this.userId = userId;
    this.content = content;
    this.timestamp = ZonedDateTime.now();
    this.latitude = latitude;
    this.longitude = longitude;
    this.localizationError = error;
  }

  public int getMsgId() {
    return msgId;
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

  public ZonedDateTime getTimestamp() {
    return timestamp;
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
