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
import com.alle.san.restaurant.models.PlaceModel;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MorePlaceRvAdapter extends RecyclerView.Adapter<MorePlaceRvAdapter.FeedViewHolder> {

    ArrayList<PlaceModel> places;
    Context context;
    ViewChanger nViewChanger;

    public MorePlaceRvAdapter(ArrayList<PlaceModel> places, Context context) {
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
        ArrayList<PlaceModel> places;
        ViewChanger viewChanger;
        
        public FeedViewHolder(@NonNull View itemView, ArrayList<PlaceModel> foodItems, ViewChanger viewChanger) {
            super(itemView);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            location = itemView.findViewById(R.id.tvLocation);
            placePic = itemView.findViewById(R.id.ivPlacePic);
            this.places = foodItems;
            this.viewChanger = viewChanger;
        }

        public void Bind (int position){
            PlaceModel place = places.get(position);
            placeName.setText(place.getName());
            location.setText(place.getLocale());
            Glide.with(itemView).load(place.getLocalePics())
                    .placeholder(R.drawable.image_icon)
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(placePic);
            itemView.setOnClickListener(v -> viewChanger.onPlaceItemClick(place, places));
        }

    }
}
