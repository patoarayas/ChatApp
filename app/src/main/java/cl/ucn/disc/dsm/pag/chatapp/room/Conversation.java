package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversations_table")
public class Conversation {

  @PrimaryKey
  @NonNull
  private int conversationId;

  private int userOneId;
  private int userTwoId;

  /**
   * Constructor.
   * @param conversationId Id of the conversation.
   * @param userOneId ID of one member of the conversation
   * @param userTwoId Id of another member of the conversation.
   */
  public Conversation(int conversationId, int userOneId, int userTwoId) {
    this.conversationId = conversationId;
    this.userOneId = userOneId;
    this.userTwoId = userTwoId;
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
}
