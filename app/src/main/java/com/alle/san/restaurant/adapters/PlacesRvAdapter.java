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
import com.alle.san.restaurant.models.PlaceModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlacesRvAdapter extends RecyclerView.Adapter<PlacesRvAdapter.PlaceViewHolder> {

    ArrayList<PlaceModel> placeItems = new ArrayList<>();
    Context context;

    public PlacesRvAdapter(ArrayList<PlaceModel> foodItems, Context context) {
        this.placeItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_place_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        holder.Bind(position);
    }

    @Override
    public int getItemCount() {
        return placeItems.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{

        TextView placeName;
        ImageView PlacePic;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.tvPlaceName);
            PlacePic = itemView.findViewById(R.id.ivFoodPic);
        }

        public void Bind (int position){
            PlaceModel placeMode = placeItems.get(position);
            placeName.setText(placeMode.getName());
            Glide.with(itemView).load(placeMode.getLocalePics())
                    .placeholder(R.drawable.image_icon)
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(PlacePic);
        }

    }
}
