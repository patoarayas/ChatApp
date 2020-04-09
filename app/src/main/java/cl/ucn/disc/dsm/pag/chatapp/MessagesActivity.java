package cl.ucn.disc.dsm.pag.chatapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageRoomModel;
import cl.ucn.disc.dsm.pag.chatapp.viewmodel.MessaggesViewModel;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

  private MessaggesViewModel messaggesViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.messages_rv_layout);

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final MessagesRecyclerViewAdapter adapter = new MessagesRecyclerViewAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    messaggesViewModel =
        new ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
            .get(MessaggesViewModel.class);

    messaggesViewModel.getAllMessages().observe(this, new Observer<List<MessageRoomModel>>() {
      @Override
      public void onChanged(List<MessageRoomModel> messageRoomModels) {
        adapter.setMessages(messageRoomModels);
      }
    });
  }
}
