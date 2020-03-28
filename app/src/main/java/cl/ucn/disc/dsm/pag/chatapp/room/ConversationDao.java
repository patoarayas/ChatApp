package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

//TODO: Implement DAO
@Dao
public interface ConversationDao {


  @Insert
  void insert(Conversation conversation);

  @Query("SELECT * FROM conversations_table WHERE userOneId = :userId OR userTwoId = :userId")
  List<Conversation> getConversationsByUserId(int userId);

}
