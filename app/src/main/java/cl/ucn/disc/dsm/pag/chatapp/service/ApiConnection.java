package cl.ucn.disc.dsm.pag.chatapp.service;

import cl.ucn.disc.dsm.pag.chatapp.service.results.AuthResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.IndexResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.ShowResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.StoreResult;
import cl.ucn.disc.dsm.pag.chatapp.service.results.models.Message;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Connection with rhe REST API.
 */
public interface ApiConnection {

  final String BASE_URL = "http://192.168.10.10/api/";

  /**
   * Call API Auth endpoint. Authenticate the user and return an API token.
   *
   * @param email    User's email
   * @param password User's password
   * @return Call with AuthResult.
   */
  @POST("auth/")
  Call<AuthResult> callAuth(
      @Query("email") final String email, @Query("password") final String password);

  /**
   * Call API Index endpoint. Returns all registered users.
   *
   * @return Call with IndexResult
   */
  @GET("chat/")
  Call<IndexResult> callIndex();

  /**
   * Call API Show endpoint. Returns user's conversations and messages.
   *
   * @param apiToken User's API token.
   * @return Call wit ShowResult
   */
  @GET("chat/{apiToken}")
  Call<ShowResult> callShow(@Path("apiToken") final String apiToken);

  /**
   * Call API Store endpoint. Store a new conversation.
   *
   * @param apiToken    User's API token.
   * @param recipientId ID of the recipient of the conversation.
   * @return CAll with StoreResult
   */
  @POST("chat/")
  Call<StoreResult> callStore(
      @Query("api_token") final String apiToken, @Query("to") final String recipientId);

  /**
   * Call API Update endpoint. Update a conversation with a new message.
   *
   * @param apiToken          User's API token.
   * @param conversationId    ID of the conversation.
   * @param content           Message's content.
   * @param latitude          Latitude localization component.
   * @param longitude         Longitude localization component.
   * @param localizationError Localization error.
   * @return Call with the created Message.
   */
  @PUT("chat/{apiToken}/")
  Call<Message> callUpdate(
      @Path("apiToken") final String apiToken,
      @Query("conversation_id") final String conversationId,
      @Query("content") final String content,
      @Query("loc_latitude") final String latitude,
      @Query("loc_longitude") final String longitude,
      @Query("loc_error") final String localizationError);
}
