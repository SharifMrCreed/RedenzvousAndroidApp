package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.utilities.ViewChanger;

public class TopScrollRvAdapter extends RecyclerView.Adapter<TopScrollRvAdapter.FeedViewHolder> {

    String[] items;
    Context context;
    ViewChanger nViewChanger;

    public TopScrollRvAdapter(String[] items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_item_button, parent, false);
        return new FeedViewHolder(itemView, items, nViewChanger);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        nViewChanger = (ViewChanger) context;
    }
    
    
    public static class FeedViewHolder extends RecyclerView.ViewHolder{

        Button name;
        String[] items;
        ViewChanger nViewChanger;
        
        public FeedViewHolder(@NonNull View itemView, String[] items, ViewChanger nViewChanger) {
            super(itemView);
            this.items = items;
            this.nViewChanger = nViewChanger;
            name = itemView.findViewById(R.id.rv_item);
        }

        public void Bind (int position){
            String itemName = items[position];
            name.setText(itemName);
            name.setOnClickListener(v -> {
                nViewChanger.onTopButtonClick(itemName);
                name.setBackgroundColor(0xfece2f);
            });
            
        }

    }
}
