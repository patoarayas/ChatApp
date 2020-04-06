package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

  @Insert
  void insert(UserRoomModel user);


  /**
   * Returns an observable list with al users on db.
   *
   * @return LiveData User list.
   */
  @Query("SELECT * FROM users_table")
  LiveData<List<UserRoomModel>> getAllUsers();

  /**
   * Returns a User by its id.
   *
   * @param id The user id.
   * @return An User
   */
  @Query("SELECT * FROM users_table WHERE id = :id")
  UserRoomModel getUserById(int id);
}
