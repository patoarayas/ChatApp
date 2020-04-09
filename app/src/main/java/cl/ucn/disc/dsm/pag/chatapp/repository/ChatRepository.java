package cl.ucn.disc.dsm.pag.chatapp.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import cl.ucn.disc.dsm.pag.chatapp.model.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.model.Message;
import cl.ucn.disc.dsm.pag.chatapp.model.User;
import cl.ucn.disc.dsm.pag.chatapp.room.ChatRoomDatabase;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationDao;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageDao;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.room.UserDao;
import cl.ucn.disc.dsm.pag.chatapp.room.UserRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.service.ChatApiService;
import cl.ucn.disc.dsm.pag.chatapp.service.ChatApiService.ChatApiException;
import cl.ucn.disc.dsm.pag.chatapp.service.Service;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.ConversationServiceModel;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.MessageServiceModel;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.UserServiceModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatRepository {

  // Singleton instance
  private static ChatRepository INSTANCE;

  // DAO
  private UserDao userDao;
  private ConversationDao conversationDao;
  private MessageDao messageDao;
  // API service
  private Service api;

  // User api token
  private String apiToken;

  // LiveData
  private LiveData<List<ConversationRoomModel>> conversations;
  private LiveData<List<UserRoomModel>> registeredUsers;

  // Executor
  private static final int NUMBER_OF_THREADS = 4;
  public static final ExecutorService repositoryExecutor = Executors
      .newFixedThreadPool(NUMBER_OF_THREADS);

  /**
   * Singleton Constructor
   *
   * @param app The application context.
   */
  private ChatRepository(final Application app) {
    // Get database instance
    ChatRoomDatabase db = ChatRoomDatabase.getDatabase(app);
    // Instantiate the Daos
    this.userDao = db.userDao();
    this.conversationDao = db.conversationDao();
    this.messageDao = db.messageDao();
    // Instantiate the api service
    this.api = new ChatApiService();


    // Fetch data from room db
    registeredUsers = userDao.getAllUsers();
    conversations = conversationDao.getAllConversations();
  }

  /**
   * Get Repository instance.
   *
   * @param app The app context.
   * @return The Repository instance (Singleton)
   */
  public static ChatRepository getRepository(final Application app) {
    if (INSTANCE == null) {
      synchronized (ChatRepository.class) {
        if (INSTANCE == null) {
          INSTANCE = new ChatRepository(app);
        }
      }
    }
    return INSTANCE;
  }

  // Public methods

  public boolean isLoged() {
    if (this.apiToken != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @return List of registered users.
   */
  public LiveData<List<UserRoomModel>> getRegisteredUsers() {
    return registeredUsers;
  }

  /**
   * @return List of conversations.
   */
  public LiveData<List<ConversationRoomModel>> getConversations() {
    return conversations;
  }

  /**
   * Get messages belonging to a conversation.
   *
   * @param conversationId Id of the conversation.
   * @return List of messages.
   */
  public LiveData<List<MessageRoomModel>> getMessagesByConversation(int conversationId) {
    return messageDao.getMessagesByConversation(conversationId);
  }

  /**
   * Creates a new conversation.
   *
   * @param receiptId Id of the receipt user of the conversation.
   */
  public void newConversation(UserRoomModel receiptId) {
    api.setNewConversation(this.apiToken, receiptId.getId());
    INSTANCE.fetchApi();
  }

  /**
   * Add a message to a conversation.
   *
   * @param msg The message.
   */
  public void addMessage(MessageRoomModel msg) {
    api.sendMessage(
        this.apiToken,
        msg.getConversationId(),
        msg.getContent(),
        msg.getLatitude(),
        msg.getLongitude(),
        msg.getLocalizationError());

    INSTANCE.fetchApi();
  }

  // FETCH API METHODS

  /** Fetch API and save data to room. */
  public void fetchApi() {
    this.fetchRegisteredUsersFromApi();
    this.fetchConversationsAndMessagesFromApi();
  }

  /**
   * Call the API Service to perform authentication.
   *
   * @param email The user email
   * @param password The user password
   * @return whether the auth was successful, also initializes the repository apiToken variable.
   */
  public boolean login(String email, String password) {
    try {
      this.apiToken = this.api.getApiToken(email, password);
      repositoryExecutor.execute(this::fetchApi);
      return true;
    } catch (ChatApiException e) {
      return false;
    }
  }

  /** Call the API to retrieve registered users and save them to the db. */
  private void fetchRegisteredUsersFromApi() {
    List<UserServiceModel> apiUsers = this.api.getRegisteredUsers();
    // Transform to room model entity.
    for (UserServiceModel usr : apiUsers) {
      UserRoomModel newUsr = new UserRoomModel(usr.id, usr.name, usr.email);

      // Call db executor to perform insertion outside the main thread
      ChatRoomDatabase.databaseWriteExecutor.execute(
          () -> {
            userDao.insert(newUsr);
          });
    }
  }

  /** Call the API and retrieve user conversation and messages and save them into the db */
  private void fetchConversationsAndMessagesFromApi() {
    if (apiToken != null) {
      List<ConversationServiceModel> conversations = this.api.getConversations(this.apiToken);
      // Conversations
      for (ConversationServiceModel conv : conversations) {
        // Transform to room model entity and insert conversation
        ConversationRoomModel newConversation = new ConversationRoomModel(conv);
        ChatRoomDatabase.databaseWriteExecutor.execute(
            () -> {
              conversationDao.insert(newConversation);
            });
        // Messages
        for (MessageServiceModel msg : conv.messages) {
          // Transform messages and insert
          MessageRoomModel newMsg = new MessageRoomModel(msg);
          ChatRoomDatabase.databaseWriteExecutor.execute(
              () -> {
                messageDao.insert(newMsg);
              });
        }
      }
    }
  }



}
