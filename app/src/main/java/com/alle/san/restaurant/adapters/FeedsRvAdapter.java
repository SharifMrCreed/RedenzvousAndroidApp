package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.food.FoodItem;
import com.alle.san.restaurant.utilities.Globals;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FeedsRvAdapter extends RecyclerView.Adapter<FeedsRvAdapter.FeedViewHolder> {

    ArrayList<FoodItem> foodItems;
    Context context;
    ViewChanger nViewChanger;

    public FeedsRvAdapter(ArrayList<FoodItem> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_foods_item, parent, false);
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
        LinearLayout parentLinearLayout;
        private final ArrayList<FoodItem> foodItems;
        ViewChanger viewChanger;
    
        public FeedViewHolder(@NonNull View itemView, ArrayList<FoodItem> foodItems, ViewChanger viewChanger) {
            super(itemView);
            parentLinearLayout = itemView.findViewById(R.id.parent_item_layout);
            foodName = itemView.findViewById(R.id.tvFoodName);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            foodPic = itemView.findViewById(R.id.iv_placePic);
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
            Bitmap bitmap = null;
            try {
                bitmap = Globals.getBitmapAt(new URL(foodItem.getImage()));
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }
            if (bitmap != null){
                Palette.from(bitmap).generate(palette ->{
                    Palette.Swatch swatch = palette.getDominantSwatch();
                    if (swatch != null){
                        GradientDrawable parentBackground = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                                new int[]{ 0x00000000, swatch.getRgb() });
                        parentLinearLayout.setBackground(parentBackground);
                        foodName.setTextColor(swatch.getTitleTextColor());
                        placeName.setTextColor(swatch.getBodyTextColor());
                    }
                });
            }
            itemView.setOnClickListener(v -> viewChanger.onFoodItemClick(foodItem, foodItems, foodPic, foodName, placeName));
        }

    }
}
