package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.viewmodel.ConversationsViewModel;
import java.util.List;

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
        .observe(
            this,
            new Observer<List<ConversationRoomModel>>() {
              @Override
              public void onChanged(List<ConversationRoomModel> conversationRoomModels) {
                adapter.setConversations(conversationRoomModels);
              }
            });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}
