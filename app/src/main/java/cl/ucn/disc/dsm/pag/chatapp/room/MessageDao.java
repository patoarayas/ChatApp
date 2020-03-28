package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

// TODO: Implement DAO
@Dao
public interface MessageDao {

  @Insert
  void insert(Message message);

  @Query("SELECT * FROM messages_table WHERE conversationId = :conversationId ORDER BY timestamp")
  List<Message> getMessages(int conversationId);

}
