package cl.ucn.disc.dsm.pag.chatapp.service.results.models;

import java.util.ArrayList;
import java.util.List;

public class ConversationServiceModel {

  public Integer conversationId;
  public List<UserServiceModel> users = new ArrayList<UserServiceModel>();
  public String createdAt;
  public List<MessageServiceModel> messages = new ArrayList<MessageServiceModel>();
}
