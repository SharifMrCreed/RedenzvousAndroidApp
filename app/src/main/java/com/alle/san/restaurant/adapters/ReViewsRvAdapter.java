package com.alle.san.restaurant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.place.PlaceReview;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;

public class ReViewsRvAdapter extends RecyclerView.Adapter<ReViewsRvAdapter.ReviewViewHolder> {
    
    ArrayList<PlaceReview> reviews;
    
    public ReViewsRvAdapter(ArrayList<PlaceReview> reviews) {
        this.reviews = reviews;
    }
    
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_review, parent, false);
        return new ReviewViewHolder(itemView, reviews);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(position);
    }
    
    @Override
    public int getItemCount() {
        return reviews.size();
    }
    
    
    public static class ReviewViewHolder extends RecyclerView.ViewHolder{
        ImageView authorPic;
        TextView tvRating, tvRelativeDate, tvComment, tvAuthorName;
        RatingBar ratingBar;
        ArrayList<PlaceReview> reviews;
        
        public ReviewViewHolder(@NonNull View itemView, ArrayList<PlaceReview> reviews) {
            super(itemView);
            authorPic = itemView.findViewById(R.id.authorImage);
            tvAuthorName = itemView.findViewById(R.id.tv_author_name);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvRelativeDate = itemView.findViewById(R.id.tv_relative_time);
            tvComment = itemView.findViewById(R.id.tv_review);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            this.reviews = reviews;
        }
        
        public  void bind(int position){
            PlaceReview placeReview = reviews.get(position);
            
            Glide.with(itemView.getContext()).load(placeReview.getProfileUrl())
                    .placeholder(R.drawable.person_icon)
                    .fallback(R.drawable.person_icon)
                    .fitCenter()
                    .into(authorPic);
            
            tvAuthorName.setText(placeReview.getAuthorName());
            tvRelativeDate.setText(placeReview.getRelativeTime());
            ratingBar.setMax(5);
            ratingBar.setRating(placeReview.getRating());
            
            tvRating.setText(String.valueOf(placeReview.getRating()));
            tvComment.setText(placeReview.getReview());
        }
    
        
    
    }
}
