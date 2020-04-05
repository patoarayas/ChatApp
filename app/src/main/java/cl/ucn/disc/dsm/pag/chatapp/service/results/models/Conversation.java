package cl.ucn.disc.dsm.pag.chatapp.service.results.models;

import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Message;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.User;
import java.util.ArrayList;
import java.util.List;

public class Conversation {

  public Integer conversationId;
  public List<User> users = new ArrayList<User>();
  public String createdAt;
  public List<Message> messages = new ArrayList<Message>();
}
