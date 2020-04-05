package cl.ucn.disc.dsm.pag.chatapp.service;

import android.util.Log;
import cl.ucn.disc.dsm.pag.chatapp.room.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.room.Message;
import cl.ucn.disc.dsm.pag.chatapp.room.User;
import cl.ucn.disc.dsm.pag.chatapp.service.models.AuthResult;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApiService implements Service {

  static final Logger LOG = LoggerFactory.getLogger(ChatApiService.class);
  private final ApiConnection connection;

  /** Constructor. */
  public ChatApiService() {
    this.connection =
        new Retrofit.Builder()
            .baseUrl(ApiConnection.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .validateEagerly(true)
            .build()
            .create(ApiConnection.class);
  }

  @Override
  public String getApiToken(String mail, String password) {

    Call<AuthResult> call = this.connection.getAuth(mail,password);

    try{
      final Response<AuthResult> response = call.execute();

      if(!response.isSuccessful()){
        throw new ChatApiException("Can't get an ChatApiResult"+ response.code(), new HttpException(response));
      }

      final AuthResult result = response.body();

      if(result.apiToken == null){
        throw new ChatApiException("No api token in ChatApiResult");
      }

      return result.apiToken;

    } catch (IOException ex){
      throw new ChatApiException("Can't get an ChatApiResult",ex);
    }

  }

  @Override
  public List<User> getRegisteredUsers() {
    return null;
  }

  @Override
  public List<Conversation> getConversations(String apiToken) {
    return null;
  }

  @Override
  public int setNewConversation(String apiToken, String userId) {
    return 0;
  }

  @Override
  public Message sendMessage(String apiToken, Message message) {
    return null;
  }

  /**
   * Exception
   */
  public static final class ChatApiException extends RuntimeException {

    public ChatApiException(String message) {
      super(message);
    }

    public ChatApiException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
