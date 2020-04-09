package cl.ucn.disc.dsm.pag.chatapp.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import cl.ucn.disc.dsm.pag.chatapp.repository.ChatRepository;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.room.UserRoomModel;
import java.util.List;

public class ConversationsViewModel extends AndroidViewModel {

  LiveData<List<ConversationRoomModel>> conversations;
  LiveData<List<UserRoomModel>> users;
  // Repository instance
  private ChatRepository repository;

  /**
   * Constructor.
   *
   * @param application App.
   */
  public ConversationsViewModel(@NonNull Application application) {
    super(application);

    this.repository = ChatRepository.getRepository(application);

    conversations = repository.getConversations();
    users = repository.getRegisteredUsers();

  }

  /**
   * Access the list of conversations.
   *
   * @return All conversations.
   */
  public LiveData<List<ConversationRoomModel>> getAllConversations() {
    return conversations;
  }

  /**
   * Acces the list of registered users.
   *
   * @return All users
   */

  public LiveData<List<UserRoomModel>> getRegisteredUsers() {
    return users;
  }

  /**
   * Add a new conversation with an user
   *
   * @param usr The receiver end of the conversation.
   */
  public void newConversation(UserRoomModel usr) {
    repository.newConversation(usr);
  }


}
