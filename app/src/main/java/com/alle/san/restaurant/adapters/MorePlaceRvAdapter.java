package com.alle.san.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.utilities.Globals;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MorePlaceRvAdapter extends RecyclerView.Adapter<MorePlaceRvAdapter.FeedViewHolder> {

    ArrayList<PlaceItem> places;
    Context context;
    ViewChanger nViewChanger;

    public MorePlaceRvAdapter(ArrayList<PlaceItem> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.rv_similar_place, parent, false);
        return new FeedViewHolder(itemView, places, nViewChanger);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
    
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        nViewChanger = (ViewChanger) context;
    }
    
    public static class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView placeName, location;
        ImageView placePic;
        LinearLayout parentLinearLayout;
        ArrayList<PlaceItem> places;
        ViewChanger viewChanger;
        
        public FeedViewHolder(@NonNull View itemView, ArrayList<PlaceItem> foodItems, ViewChanger viewChanger) {
            super(itemView);
    
            parentLinearLayout = itemView.findViewById(R.id.parent_item_layout);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            location = itemView.findViewById(R.id.tvLocation);
            placePic = itemView.findViewById(R.id.ivPlacePic);
            this.places = foodItems;
            this.viewChanger = viewChanger;
        }

        public void Bind (int position){
            PlaceItem place = places.get(position);
            placeName.setText(place.getName());
            location.setText(place.getVicinity());
            Glide.with(itemView).load(Globals.getLink(place.getPhotos().get(0)))
                    .placeholder(R.drawable.image_icon)
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(placePic);
            
            itemView.setOnClickListener(v -> viewChanger.onPlaceItemClick(place, places, placePic, placeName));
            
        }

    }
}
