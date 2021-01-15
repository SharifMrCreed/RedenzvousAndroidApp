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
import com.alle.san.restaurant.utilities.ViewChanger;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SimilarRvAdapter extends RecyclerView.Adapter<SimilarRvAdapter.FeedViewHolder> {

    ArrayList<FoodItem> foodItems;
    Context context;
    ViewChanger nViewChanger;

    public SimilarRvAdapter(ArrayList<FoodItem> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_similar_food, parent, false);
        return new FeedViewHolder(itemView, foodItems, nViewChanger);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
    
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        nViewChanger = (ViewChanger) context;
    }
    
    public static class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView foodName, placeName;
        ImageView foodPic;
        ArrayList<FoodItem> foodItems;
        ViewChanger viewChanger;
        
        public FeedViewHolder(@NonNull View itemView, ArrayList<FoodItem> foodItems, ViewChanger viewChanger) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tvFoodName);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            foodPic = itemView.findViewById(R.id.ivFoodPic);
            this.foodItems = foodItems;
            this.viewChanger = viewChanger;
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
            itemView.setOnClickListener(v -> viewChanger.onFoodItemClick(foodItem, foodItems));
        }

    }
}
