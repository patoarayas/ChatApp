package cl.ucn.disc.dsm.pag.chatapp.service;

import cl.ucn.disc.dsm.pag.chatapp.service.results.AuthResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.IndexResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.ShowResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.StoreResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Message;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.User;
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

    Call<AuthResult> call = this.connection.callAuth(mail, password);

    try {
      final Response<AuthResult> response = call.execute();

      if (!response.isSuccessful()) {
        throw new ChatApiException("Unsuccessful response: " + response.code(),
            new HttpException(response));
      }

      final AuthResult result = response.body();

      if (result == null) {
        throw new ChatApiException("Null response result.");
      }

      if (result.apiToken == null) {
        throw new ChatApiException("No api token in response result.");
      }

      return result.apiToken;

    } catch (IOException ex){
      throw new ChatApiException("Can't get an Response", ex);
    }

  }

  @Override
  public List<User> getRegisteredUsers() {
    Call<IndexResult> call = this.connection.callIndex();

    try {
      final Response<IndexResult> response = call.execute();
      final IndexResult result = response.body();

      if (result == null) {
        throw new ChatApiException("Null response result.");
      }

      if (result.users == null) {
        throw new ChatApiException("No users in response result.");
      }

      return result.users;

    } catch (IOException ex) {
      throw new ChatApiException("Can't get a Response", ex);
    }
  }

  @Override
  public List<Conversation> getConversations(String apiToken) {
    Call<ShowResult> call = this.connection.callShow(apiToken);

    try {
      final Response<ShowResult> response = call.execute();
      final ShowResult result = response.body();

      if (result == null) {
        throw new ChatApiException("Null response result.");
      }

      if (result.conversations == null) {
        throw new ChatApiException("No users in response result.");
      }

      return result.conversations;

    } catch (IOException ex) {
      throw new ChatApiException("Can't get a Response", ex);
    }
  }

  @Override
  public int setNewConversation(String apiToken, String recipientId) {
    Call<StoreResult> call = this.connection.callStore(apiToken, recipientId);

    try {
      final Response<StoreResult> response = call.execute();
      final StoreResult result = response.body();

      if (result == null) {
        throw new ChatApiException("Null response result.");
      }

      return result.conversationId;

    } catch (IOException ex) {
      throw new ChatApiException("Can't get a Response", ex);
    }
  }


  @Override
  public Message sendMessage(String apiToken, String conversationId, String content,
      String latitude, String longitude, String localizationError) {
    Call<Message> call = this.connection
        .callUpdate(apiToken, conversationId, content, latitude, longitude, localizationError);

    try {
      final Response<Message> response = call.execute();
      final Message result = response.body();

      if (result == null) {
        throw new ChatApiException("Null response result.");
      }

      return result;

    } catch (IOException ex) {
      throw new ChatApiException("Can't get a Response", ex);
    }
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
