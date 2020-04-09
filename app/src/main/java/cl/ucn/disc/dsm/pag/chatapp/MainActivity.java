package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import cl.ucn.disc.dsm.pag.chatapp.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

  // Logger
  public static Logger LOG = LoggerFactory.getLogger(MainActivity.class);
  // Repository
  private ChatRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    repository = ChatRepository.getRepository(this.getApplication());
  }

  public void login(View view) {
    Intent intent = new Intent(this, ConversationActivity.class);

    EditText mailInput = (EditText) findViewById(R.id.login_email_input);
    EditText passInput = (EditText) findViewById(R.id.login_pass_input);

    String email = mailInput.getText().toString();
    String pass = passInput.getText().toString();

    LOG.debug("Email: " + email + " Pass: " + pass);

    AsyncTask.execute(() -> {
      repository.login(email, pass);
    });

    if (repository.isLoged()) {
      LOG.debug("Loged in");
      startActivity(intent);
    }
  }
}
