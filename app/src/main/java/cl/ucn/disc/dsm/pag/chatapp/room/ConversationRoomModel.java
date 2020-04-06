package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.ConversationServiceModel;
import org.threeten.bp.ZonedDateTime;

@Entity(tableName = "conversations_table")
public class ConversationRoomModel {

  @PrimaryKey

  private int conversationId;

  private int userOneId;
  private int userTwoId;
  // TODO: Add DateConverter ?
  private String createdAt;


  /**
   * Constructor.
   *
   * @param conversationId Id of the conversation.
   * @param userOneId      ID of one member of the conversation
   * @param userTwoId      Id of another member of the conversation.
   * @param createdAt      Timestamp
   */
  public ConversationRoomModel(int conversationId, int userOneId, int userTwoId, String createdAt) {
    this.conversationId = conversationId;
    this.userOneId = userOneId;
    this.userTwoId = userTwoId;
    this.createdAt = createdAt;
  }

  /**
   * Constructor from ConversationServiceModel
   *
   * @param conversation An ConversationServiceModel conversation
   */
  @Ignore
  public ConversationRoomModel(ConversationServiceModel conversation) {
    this.conversationId = conversation.conversationId;
    this.userOneId = conversation.users.get(0).id;
    this.userTwoId = conversation.users.get(1).id;
    this.createdAt = conversation.createdAt;
  }

  public int getConversationId() {
    return conversationId;
  }

  public int getUserOneId() {
    return userOneId;
  }

  public int getUserTwoId() {
    return userTwoId;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
