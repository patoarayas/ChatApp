package cl.ucn.disc.dsm.pag.chatapp.service.results;

import cl.ucn.disc.dsm.pag.chatapp.service.results.models.MessageServiceModel;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.UserServiceModel;
import java.util.ArrayList;
import java.util.List;

public class StoreResult {

  public Integer conversationId;
  public List<UserServiceModel> users = new ArrayList<UserServiceModel>();
  public String createdAt;
  public List<MessageServiceModel> messages = new ArrayList<MessageServiceModel>();
}
