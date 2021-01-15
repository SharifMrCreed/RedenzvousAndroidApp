package com.alle.san.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.adapters.ChatRvAdapter;
import com.alle.san.restaurant.models.ChatModel;
import com.alle.san.restaurant.utilities.ChatUtil;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.alle.san.restaurant.utilities.ChatUtil.*;

public class ChatsFragment extends Fragment {

    RecyclerView rvChat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chats_fragment, container, false);
        rvChat = view.findViewById(R.id.chat_rv);
        rvChat.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        rvChat.setAdapter(new ChatRvAdapter(chats(), getContext()));


        return view;
    }
    private ArrayList<ChatModel> chats(){
        ArrayList<ChatModel> allChats = new ArrayList<>();
        for (int i = 0; i< CHAT_NAMES.length; i++){
            ChatModel chat = new ChatModel(CHAT_NAMES[i], TIME_STAMPS[i], MESSAGES[i], SENDERS[i]  + ": ", DPs[i]);
            allChats.add(chat);
        }
        return allChats;
    }
}
