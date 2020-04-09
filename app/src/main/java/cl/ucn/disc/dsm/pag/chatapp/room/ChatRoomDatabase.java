package cl.ucn.disc.dsm.pag.chatapp.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import cl.ucn.disc.dsm.pag.chatapp.repository.ChatRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserRoomModel.class, ConversationRoomModel.class,
    MessageRoomModel.class}, version = 1, exportSchema = false)

public abstract class ChatRoomDatabase extends RoomDatabase {

  // Daos abstract getters
  public abstract UserDao userDao();

  public abstract ConversationDao conversationDao();

  public abstract MessageDao messageDao();

  // SINGLETON
  private static volatile ChatRoomDatabase INSTANCE;

  // Executor
  private static final int NUMBER_OF_THREADS = 4;
  public static final ExecutorService databaseWriteExecutor = Executors
      .newFixedThreadPool(NUMBER_OF_THREADS);

  // Callback
  private static RoomDatabase.Callback ChatRoomDbCallBack = new RoomDatabase.Callback() {
    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  };

  /**
   * Instanciate the database if it isn't already instantiated.
   *
   * @param context Application context
   * @return The Singleton
   */
  public static ChatRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (ChatRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ChatRoomDatabase.class,
              "chat_database")
              .addCallback(ChatRoomDbCallBack)
              .build();
        }
      }
    }
    return INSTANCE;
  }


}
