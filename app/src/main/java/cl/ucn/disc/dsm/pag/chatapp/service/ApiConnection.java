package cl.ucn.disc.dsm.pag.chatapp.service;

import cl.ucn.disc.dsm.pag.chatapp.service.models.AuthResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiConnection {

  final String BASE_URL = "http://192.168.10.10/api/";

  @POST("auth/")
  Call<AuthResult> getAuth(@Query("email") final String email, @Query("password") final String password);


}
