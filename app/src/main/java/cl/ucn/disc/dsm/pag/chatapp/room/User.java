package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

  @PrimaryKey
  private int userId;

  private String username;
  private String mail;
  private String password;

  /**
   * Constructor.
   * @param userId ID of the user
   * @param username Username
   * @param mail Mail of the user
   * @param password Password
   */
  public User(int userId, String username, String mail, String password) {
    this.userId = userId;
    this.username = username;
    this.mail = mail;
    this.password = password;
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getMail() {
    return mail;
  }

  public String getPassword() {
    return password;
  }
}
