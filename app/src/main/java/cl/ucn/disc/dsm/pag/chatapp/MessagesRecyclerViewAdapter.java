package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.chatapp.MessagesRecyclerViewAdapter.MessagesViewHolder;
import cl.ucn.disc.dsm.pag.chatapp.room.MessageRoomModel;
import java.util.List;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesViewHolder> {

  private final LayoutInflater inflater;
  private List<MessageRoomModel> messages;

  public MessagesRecyclerViewAdapter(Context context) {
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = inflater.inflate(R.layout.messages_rv_item_layout, parent, false);
    return new MessagesViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
    if (messages != null) {
      MessageRoomModel current = messages.get(position);
      holder.messageItemView.setText(current.getContent());

    } else {
      holder.messageItemView.setText("No messages...");
    }
  }

  public void setMessages(List<MessageRoomModel> msgs) {
    messages = msgs;
  }

  @Override
  public int getItemCount() {
    if (messages != null) {
      return messages.size();
    } else {
      return 0;
    }
  }

  class MessagesViewHolder extends RecyclerView.ViewHolder {

    private final TextView messageItemView;

    public MessagesViewHolder(@NonNull View itemView) {
      super(itemView);
      messageItemView = itemView.findViewById(R.id.textView);
    }
  }
}
