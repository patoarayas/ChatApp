package cl.ucn.disc.dsm.pag.chatapp.service;

import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Message;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatApiServiceTest {

  // Logger
  static final Logger LOG = LoggerFactory.getLogger(ChatApiService.class);

  @Test
  public void getApiTokenTest() {

    Service service = new ChatApiService();
    String token = service.getApiToken("usuario1@usuarios.cl", "usuario1");
    LOG.debug(token);
    Assertions.assertNotNull(token);
  }

  @Test
  public void getRegisteredUsersTest() {
    Service service = new ChatApiService();

    List<User> users = service.getRegisteredUsers();
    for (User usr : users) {
      LOG.debug(usr.name);
    }
    Assertions.assertNotNull(users);
  }

  @Test
  public void getConversationsTest() {
    Service service = new ChatApiService();

    String apiToken = service.getApiToken("usuario1@usuarios.cl", "usuario1");
    List<Conversation> conversations = service.getConversations(apiToken);

    for (Conversation conv : conversations) {
      LOG.debug("Conversation id: " + Integer.toString(conv.conversationId));
    }
    Assertions.assertNotNull(conversations);
  }

  @Test
  public void setNewConversationTest() {
    Service service = new ChatApiService();

    String apiToken = service.getApiToken("usuario1@usuarios.cl", "usuario1");
    int conversationId = service.setNewConversation(apiToken, "3");
    LOG.debug("Conversation ID:" + Integer.toString(conversationId));
  }

  @Test
  public void sendMessageTest() {
    Service service = new ChatApiService();

    String apiToken = service.getApiToken("usuario1@usuarios.cl", "usuario1");
    Message msg = service.sendMessage(apiToken, "2", "Enviado desde android",
        "13", "12", "0");
    LOG.debug("Message: " + "From: " + msg.userId + "Content: " + msg.content);
    Assertions.assertNotNull(msg);

  }


}
