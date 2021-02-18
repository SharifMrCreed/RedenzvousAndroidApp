package com.alle.san.restaurant.homeViews;

import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.MorePlaceRvAdapter;
import com.alle.san.restaurant.adapters.ReViewsRvAdapter;
import com.alle.san.restaurant.models.place.PlaceDetailResult;
import com.alle.san.restaurant.models.place.PlaceDetails;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.models.place.PlaceReview;
import com.alle.san.restaurant.repo.RetroFetch;
import com.alle.san.restaurant.utilities.ApiParams;
import com.alle.san.restaurant.utilities.Globals;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlaceItemFragment extends Fragment {
    ConstraintLayout parentLayout;
    RecyclerView similarItems, reviews_RV;
    ImageView placePic;
    TextView vicinity, tvRating, tvRaters, tvPhoneNumber, tvOpenMap, tvWebsite,
            placeName, tvFree, tvReview, tvFair, tvModerate, tvPricey, tvHighEnd;
    PlaceItem nPlaceItem = new PlaceItem();
    RatingBar ratingBar;
    PlaceDetails placeDetails = new PlaceDetails();
    ArrayList<PlaceItem> morePlaces = new ArrayList<>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            nPlaceItem = bundle.getParcelable(Globals.PLACE_ITEM);
            morePlaces = bundle.getParcelableArrayList(Globals.PLACE_ITEMS);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.enter_transition));
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.enter_transition));
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_item, container, false);
        
        initViews(view);
        
        initRecyclerViews();
        
        populateViews();
        
        
        return view;
    }
    
    private void getPlaceDetails(String placeId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiParams.PLACES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFetch retroFetch = retrofit.create(RetroFetch.class);
        Call<PlaceDetailResult> call = retroFetch.getPlaceDetails(ApiParams.PLACE_API_KEY, placeId, ApiParams.FIELDS);
        call.enqueue(new Callback<PlaceDetailResult>() {
            @Override
            public void onResponse(Call<PlaceDetailResult> call, Response<PlaceDetailResult> response) {
                if(response.isSuccessful()) {
                    PlaceDetailResult result = response.body();
                    if(result.getResult().getPlaceReview() != null){
                        initReviewsRecyclerView(result.getResult());
                    }else{
                        tvReview.setText("No Reviews");
                    }
                    putDetails(result.getResult());
                }else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }
            }
            
            @Override
            public void onFailure(Call<PlaceDetailResult> call, Throwable t) {
                
                Toast.makeText(getContext(), "failed to connect", Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private void putDetails(PlaceDetails result) {
        switch(result.getPriceLevel()) {
            case (1):
                tvFair.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape_filled, null));
                break;
            case (2):
                tvModerate.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape_filled, null));
                break;
            case (3):
                tvPricey.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape_filled, null));
                break;
            case (4):
                tvHighEnd.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape_filled, null));
                break;
            default:
                tvFree.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_shape_filled, null));
        }
            tvPhoneNumber.setText(result.getIntPhoneNumber());
            tvWebsite.setText(result.getBusinessWebsite());
        
    }
    
    
    private void populateViews() {
        getPlaceDetails(nPlaceItem.getPlaceId());
        Glide.with(getContext()).load(Globals.getLink(nPlaceItem.getPhotos().get(0)))
                .placeholder(R.drawable.image_icon)
                .fallback(R.drawable.broken_image_icon)
                .into(placePic);
        vicinity.setText(nPlaceItem.getVicinity());
        placeName.setText(nPlaceItem.getName());
        
        ratingBar.setMax(5);
        ratingBar.setRating(nPlaceItem.getRating());
        tvRating.setText(String.valueOf(nPlaceItem.getRating()));
        String raters = "(" + nPlaceItem.getTotalRating() + ")";
        tvRaters.setText(raters);
        
        if(placeDetails != null) {
        
        }
        
        
    }
    
    private void initReviewsRecyclerView(PlaceDetails placeDetails) {
        ArrayList<PlaceReview> reviews = new ArrayList<>(placeDetails.getPlaceReview());
        reviews_RV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        reviews_RV.setAdapter(new ReViewsRvAdapter(reviews));
    }
    
    private void initRecyclerViews() {
        similarItems.setAdapter(new MorePlaceRvAdapter(morePlaces, getContext()));
        similarItems.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }
    
    private void initViews(View view) {
        placePic = view.findViewById(R.id.place_image);
        placeName = view.findViewById(R.id.tvPlaceName);
        tvRating = view.findViewById(R.id.tv_rating);
        tvRaters = view.findViewById(R.id.tv_raters);
        tvReview = view.findViewById(R.id.tv_review);
        ratingBar = view.findViewById(R.id.rating_bar);
        tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
        tvOpenMap = view.findViewById(R.id.tv_open_map);
        tvWebsite = view.findViewById(R.id.tv_website);
        tvFree = view.findViewById(R.id.tv_free);
        tvFair = view.findViewById(R.id.tv_fair);
        tvModerate = view.findViewById(R.id.tv_moderate);
        tvPricey = view.findViewById(R.id.tv_pricey);
        tvHighEnd = view.findViewById(R.id.tv_high_end);
        reviews_RV = view.findViewById(R.id.rv_reviews);
        vicinity = view.findViewById(R.id.tv_vicinity);
        similarItems = view.findViewById(R.id.rv_similar_items);
        parentLayout = view.findViewById(R.id.place_item_parent);
    }
    
}