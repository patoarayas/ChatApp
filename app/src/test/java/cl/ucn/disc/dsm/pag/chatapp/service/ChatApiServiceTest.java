package cl.ucn.disc.dsm.pag.chatapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatApiServiceTest {

  // Logger
  static final Logger LOG = LoggerFactory.getLogger(ChatApiService.class);

  @Test
  public void getApiTokenTest(){

    Service service = new ChatApiService();
    String token = service.getApiToken("usuario1@usuarios.cl", "usuario1");
    LOG.debug(token);
    Assertions.assertNotNull(token);
  }


}
