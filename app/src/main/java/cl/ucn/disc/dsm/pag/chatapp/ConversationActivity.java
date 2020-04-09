package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.chatapp.viewmodel.ConversationsViewModel;

public class ConversationActivity extends AppCompatActivity {

  public static final int NEW_CONVERSATION_ACTIVITY_REQUEST_CODE = 1;

  private ConversationsViewModel conversationsViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.conversations_rv_layout);

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final ConversationsRecyclerViewAdapter adapter = new ConversationsRecyclerViewAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    conversationsViewModel =
        new ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
            .get(ConversationsViewModel.class);

    conversationsViewModel
        .getAllConversations()
        .observe(this, adapter::setConversations);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == NEW_CONVERSATION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
      // New conversation and go to activity message
    }
  }
}
