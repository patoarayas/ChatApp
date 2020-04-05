package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.threeten.bp.ZonedDateTime;

@Entity(tableName = "conversations_table")
public class Conversation {

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
  public Conversation(int conversationId, int userOneId, int userTwoId, String createdAt) {
    this.conversationId = conversationId;
    this.userOneId = userOneId;
    this.userTwoId = userTwoId;
    this.createdAt = createdAt;
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
