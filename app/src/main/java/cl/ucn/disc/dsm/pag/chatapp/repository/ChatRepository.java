package cl.ucn.disc.dsm.pag.chatapp.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import cl.ucn.disc.dsm.pag.chatapp.room.ChatRoomDatabase;
import cl.ucn.disc.dsm.pag.chatapp.room.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationDao;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageDao;
import cl.ucn.disc.dsm.pag.chatapp.room.User;
import cl.ucn.disc.dsm.pag.chatapp.room.UserDao;
import java.util.List;

public class ChatRepository {

  private UserDao userDao;
  private ConversationDao conversationDao;
  private MessageDao messageDao;

  // TODO: Implement user session persistence with room.

  private String apiToken;
  private User user;

  private LiveData<List<Conversation>> allConversations;

  /**
   * Constructor
   * @param app The context
   */
  public ChatRepository(Application app) {
    ChatRoomDatabase db = ChatRoomDatabase.getDatabase(app);
    // Instantiate the Daos
    this.userDao = db.userDao();
    this.conversationDao = db.conversationDao();
    this.messageDao = db.messageDao();
  }


}
