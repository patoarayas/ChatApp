package cl.ucn.disc.dsm.pag.chatapp.service;

import cl.ucn.disc.dsm.pag.chatapp.room.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.room.Message;
import cl.ucn.disc.dsm.pag.chatapp.room.User;
import java.util.List;

public interface Service {

  // TODO: implement service model

  /**
   * Authenticate the user, get the API token.
   *
   * @param mail User mail
   * @param password User password
   * @return An API token
   */
  String getApiToken(String mail, String password);

  /**
   * Get the list of registered users.
   * @return List of Users
   */
  List<User> getRegisteredUsers();

  /**
   * Get user's conversations.
   * @param apiToken The user api token
   * @return List of conversations
   */
  List<Conversation> getConversations(String apiToken);

  /**
   * Creates a new conversation.
   * @param apiToken The user api token
   * @param userId The id of the receiver user of the conversation.
   * @return  id of the conversation.
   */
  int setNewConversation(String apiToken, String userId);


 // TODO: This method.
  Message sendMessage(String apiToken, Message message );






}
