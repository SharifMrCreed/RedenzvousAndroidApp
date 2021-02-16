package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.alle.san.restaurant.MainActivity;
import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.models.place.PlacePhoto;
import com.alle.san.restaurant.utilities.Globals;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlaceItemAdapter extends RecyclerView.Adapter<PlaceItemAdapter.FeedViewHolder>{
    ArrayList<PlaceItem> placeItems;
    Context context;
    
    public PlaceItemAdapter(ArrayList<PlaceItem> placeItems, Context context) {
        this.placeItems = placeItems;
        this.context = context;
    }
    
    
    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_place_item, parent, false);
        return new FeedViewHolder(itemView, placeItems);
    }
    
    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.Bind(position);
    }
    
    @Override
    public int getItemCount() {
        return placeItems.size();
    }
    
    public void updateItems(ArrayList<PlaceItem> placeItems){
        this.placeItems.addAll(placeItems);
        notifyDataSetChanged();
    }
    
    public static class FeedViewHolder extends RecyclerView.ViewHolder{
        
        TextView PlaceName, types, vicinity;
        ImageView placePic;
        RatingBar nRatingBar;
        private final ArrayList<PlaceItem> placeItems;
        
        public FeedViewHolder(@NonNull View itemView, ArrayList<PlaceItem> placeItems) {
            super(itemView);
            PlaceName = itemView.findViewById(R.id.tvPlaceName);
            vicinity = itemView.findViewById(R.id.tv_vicinity);
            types = itemView.findViewById(R.id.tv_type);
            placePic = itemView.findViewById(R.id.iv_placePic);
            nRatingBar = itemView.findViewById(R.id.rating_bar);
            this.placeItems = placeItems;
        }
        
        public void Bind (int position){
            PlaceItem place = placeItems.get(position);
            PlaceName.setText(place.getName());
            types.setText("");
            for (String type:place.getTypes()) {
                types.append(type);
                types.append(", ");
            }
            vicinity.setText(place.getVicinity());
            nRatingBar.setMax(5);
            nRatingBar.setRating(place.getRating());
            PlacePhoto placePhoto;
            if (place.getPhotos() != null){
                placePhoto = place.getPhotos()[0];
                Glide.with(itemView).load(Globals.getLink(placePhoto))
                        .placeholder(R.drawable.image_icon)
                        .fallback(R.drawable.broken_image_icon)
                        .into(placePic);
            }else{
                Glide.with(itemView).load(R.drawable.aa)
                        .placeholder(R.drawable.image_icon)
                        .fallback(R.drawable.broken_image_icon)
                        .into(placePic);
            }
    
            
        }
        
        
    }
}

