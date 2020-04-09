package cl.ucn.disc.dsm.pag.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
   * @param conversationsList List of converswtions.
   */
  public void setConversations(List<ConversationRoomModel> conversationsList) {
    conversations = conversationsList;
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
      holder.conversationItemView.setText(current.getUserOneId());
    } else {
      holder.conversationItemView.setText(R.string.empty_conversations);
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
  static class ConversationViewHolder extends RecyclerView.ViewHolder {

    private final TextView conversationItemView;

    public ConversationViewHolder(@NonNull View itemView) {
      super(itemView);
      conversationItemView = itemView.findViewById(R.id.textView);
    }
  }

}
