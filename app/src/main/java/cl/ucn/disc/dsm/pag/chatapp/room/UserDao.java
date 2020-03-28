package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

//TODO: Implement Dao
@Dao
public interface UserDao {

  @Insert
  void insert(User user);

  @Query("SELECT * FROM users_table WHERE userId = :userId")
  User getUserById(int userId);

  @Query("SELECT * FROM users_table WHERE mail = :mail AND password = :password")
  User getUserByAuth(String mail, String password);
}
