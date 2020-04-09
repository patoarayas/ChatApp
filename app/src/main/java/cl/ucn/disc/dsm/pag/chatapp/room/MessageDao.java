package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

// TODO: Implement DAO
@Dao
public interface MessageDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(MessageRoomModel message);

  @Query("SELECT * FROM messages_table WHERE conversationId = :conversationId ORDER BY createdAt")
  LiveData<List<MessageRoomModel>> getMessagesByConversation(int conversationId);

}
