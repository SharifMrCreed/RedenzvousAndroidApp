package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.ChatModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChatRvAdapter extends RecyclerView.Adapter<ChatRvAdapter.ChatViewHolder> {

    ArrayList<ChatModel> allChats;
    Context context;

    public ChatRvAdapter(ArrayList<ChatModel> allChats, Context context) {
        this.allChats = allChats;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.rv_chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return allChats.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        ImageView chatDp;
        TextView chatName, chatTime, lastMessage, lastSender;

        public ChatViewHolder(@NonNull View view) {
            super(view);
            chatDp = view.findViewById(R.id.chat_dp);
            chatName = view.findViewById(R.id.chat_name);
            chatTime = view.findViewById(R.id.time_stamp);
            lastMessage = view.findViewById(R.id.recent_message);
            lastSender = view.findViewById(R.id.message_sender);

        }

        public void Bind(int position){
            ChatModel chat = allChats.get(position);
            chatTime.setText(chat.getMessageTime());
            chatName.setText(chat.getChatName());
            lastSender.setText(chat.getLastSender());
            lastMessage.setText(chat.getLastMessage());
            Glide.with(itemView).load(chat.getChatDp())
                    .placeholder(R.drawable.allecon)
                    .centerCrop()
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(chatDp);


        }
    }
}
