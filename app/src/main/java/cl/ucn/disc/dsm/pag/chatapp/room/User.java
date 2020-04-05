package cl.ucn.disc.dsm.pag.chatapp.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

  @PrimaryKey
  private int id;
  private String name;
  private String mail;


  /**
   * Constructor.
   *
   * @param id   ID of the user
   * @param name Username
   * @param mail Mail of the user
   */
  public User(int id, String name, String mail) {
    this.id = id;
    this.name = name;
    this.mail = mail;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMail() {
    return mail;
  }
}
