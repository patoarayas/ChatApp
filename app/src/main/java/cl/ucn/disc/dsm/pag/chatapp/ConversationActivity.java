package cl.ucn.disc.dsm.pag.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ConversationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.conversations_rv_layout);
  }
}