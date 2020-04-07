package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.pag.chatapp.model.Conversation;
import cl.ucn.disc.dsm.pag.chatapp.room.ConversationRoomModel;
import java.util.List;

public class ConversationsRecyclerViewAdapter
    extends RecyclerView.Adapter<ConversationsRecyclerViewAdapter.ConversationViewHolder> {

  private final LayoutInflater inflater;
  private List<ConversationRoomModel> conversations;

  /**
   * Constructor.
   *
   * @param context context.
   */
  public ConversationsRecyclerViewAdapter(Context context) {
    inflater = LayoutInflater.from(context);
  }

  /**
   * Set conversations.
   *
   * @param conversations
   */
  public void setConversations(List<ConversationRoomModel> conversations) {
    conversations = conversations;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = inflater.inflate(R.layout.conversation_rv_item_layout, parent, false);
    return new ConversationViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
    if (conversations != null) {
      ConversationRoomModel current = conversations.get(position);
      holder.convesationItemView.setText(current.getUserOneId());
    } else {
      holder.convesationItemView.setText("No hay conversaciones.");
    }
  }

  @Override
  public int getItemCount() {
    if (conversations != null) {
      return conversations.size();
    } else {
      return 0;
    }
  }


  /**
   * ViewHolder for RecyclerView Items.
   */
  class ConversationViewHolder extends RecyclerView.ViewHolder {

    private final TextView convesationItemView;

    public ConversationViewHolder(@NonNull View itemView) {
      super(itemView);
      convesationItemView = itemView.findViewById(R.id.textView);
    }
  }

}
