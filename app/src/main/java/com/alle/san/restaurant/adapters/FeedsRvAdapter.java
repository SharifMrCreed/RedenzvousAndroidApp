package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.FoodItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeedsRvAdapter extends RecyclerView.Adapter<FeedsRvAdapter.FeedViewHolder> {

    ArrayList<FoodItem> foodItems = new ArrayList<>();
    Context context;

    public FeedsRvAdapter(ArrayList<FoodItem> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_feeds_item, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView foodName, placeName;
        ImageView foodPic;
        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tvFoodName);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            foodPic = itemView.findViewById(R.id.ivFoodPic);
        }

        public void Bind (int position){
            FoodItem foodItem = foodItems.get(position);
            foodName.setText(foodItem.getName());
            placeName.setText(foodItem.getRestaurantName());
            Glide.with(itemView).load(foodItem.getImage())
                    .placeholder(R.drawable.image_icon)
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(foodPic);
        }

    }
}
