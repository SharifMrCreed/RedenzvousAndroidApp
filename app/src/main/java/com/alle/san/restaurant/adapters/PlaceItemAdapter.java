package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.models.place.PlacePhoto;
import com.alle.san.restaurant.utilities.Globals;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlaceItemAdapter extends RecyclerView.Adapter<PlaceItemAdapter.FeedViewHolder>{
    ArrayList<PlaceItem> placeItems;
    Context context;
    ViewChanger nViewChanger;
    
    public PlaceItemAdapter(ArrayList<PlaceItem> placeItems, Context context) {
        this.placeItems = placeItems;
        this.context = context;
    }
    
    
    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_place_item, parent, false);
        return new FeedViewHolder(itemView, placeItems, nViewChanger);
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
    
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        nViewChanger = (ViewChanger) context;
    }
    
    public static class FeedViewHolder extends RecyclerView.ViewHolder{
        
        TextView placeName, vicinity, rating, raters;
        ImageView placePic;
        RatingBar nRatingBar;
        LinearLayout parentLinearLayout;
        ViewChanger nViewChanger;
        private final ArrayList<PlaceItem> placeItems;
        
        public FeedViewHolder(@NonNull View itemView, ArrayList<PlaceItem> placeItems, ViewChanger viewChanger) {
            super(itemView);
            parentLinearLayout = itemView.findViewById(R.id.parent_item_layout);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            vicinity = itemView.findViewById(R.id.tv_vicinity);
            rating = itemView.findViewById(R.id.tv_rating);
            raters = itemView.findViewById(R.id.tv_raters);
            placePic = itemView.findViewById(R.id.iv_placePic);
            nRatingBar = itemView.findViewById(R.id.rating_bar);
            this.placeItems = placeItems;
            nViewChanger = viewChanger;
        }
        
        public void Bind (int position){
            
            PlaceItem place = placeItems.get(position);
            itemView.setOnClickListener(view -> nViewChanger.onPlaceItemClick(place, placeItems, placePic, placeName));
            placeName.setText(place.getName());
            rating.setText(String.valueOf(place.getRating()));
            String ratersNumber = "(" + place.getTotalRating() + ")";
            raters.setText(ratersNumber);
            vicinity.setText(place.getVicinity());
            nRatingBar.setMax(5);
            nRatingBar.setRating(place.getRating());
            PlacePhoto placePhoto;
            if (place.getPhotos() != null){
                placePhoto = place.getPhotos().get(0);
                Glide.with(itemView).load(Globals.getLink(placePhoto))
                        .placeholder(R.drawable.image_icon)
                        .fallback(R.drawable.broken_image_icon)
                        .into(placePic);
            }else{
                Glide.with(itemView.getContext()).load(R.drawable.aa)
                        .placeholder(R.drawable.image_icon)
                        .fallback(R.drawable.broken_image_icon)
                        .into(placePic);
            }
    
            
        }
        
        
    }
}

