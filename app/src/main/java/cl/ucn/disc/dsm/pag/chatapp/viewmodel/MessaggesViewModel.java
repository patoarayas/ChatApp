package cl.ucn.disc.dsm.pag.chatapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import cl.ucn.disc.dsm.pag.chatapp.repository.ChatRepository;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageRoomModel;
import java.util.List;

public class MessaggesViewModel extends AndroidViewModel {

  private int convId;
  private ChatRepository repository;
  private LiveData<List<MessageRoomModel>> messages;

  public MessaggesViewModel(Application app) {
    super(app);
    repository = ChatRepository.getRepository(app);
    messages = repository.getMessagesByConversation(convId);
  }

  public LiveData<List<MessageRoomModel>> getAllMessages() {
    return messages;
  }

  public void insert(MessageRoomModel msg) {
    repository.addMessage(msg);
  }
}
