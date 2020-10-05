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
import com.alle.san.restaurant.models.AdModel;
import com.alle.san.restaurant.models.PlaceModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdRvAdapter extends RecyclerView.Adapter<AdRvAdapter.PlaceViewHolder> {

    ArrayList<AdModel> placeItems;
    Context context;

    public AdRvAdapter(ArrayList<AdModel> foodItems, Context context) {
        this.placeItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_ad_item, parent, false);
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
        ImageView PlacePic;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            PlacePic = itemView.findViewById(R.id.ivFoodPic);
        }

        public void Bind (int position){
            AdModel placeMode = placeItems.get(position);
            Glide.with(itemView).load(placeMode.getSnap())
                    .placeholder(R.drawable.image_icon)
                    .centerCrop()
                    .fallback(R.drawable.image_icon)
                    .error(R.drawable.broken_image_icon)
                    .into(PlacePic);
        }

    }
}
