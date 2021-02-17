package com.alle.san.restaurant.homeViews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlaceItemFragment extends Fragment {
    ConstraintLayout parentLayout;
    RecyclerView similarItems, reviews_RV;
    ImageView placePic;
    TextView vicinity, tvRating, tvRaters, tvPhoneNumber, tvOpenMap, tvGoogleSite,
            tvWebsite, placeName, tvFree, tvFair, tvModerate, tvPricey, tvHighEnd;
    PlaceItem nPlaceItem = new PlaceItem();
    RatingBar ratingBar;
    ArrayList<PlaceItem> morePlaces = new ArrayList<>();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            nPlaceItem = bundle.getParcelable(Globals.PLACE_ITEM);
            morePlaces = bundle.getParcelableArrayList(Globals.PLACE_ITEMS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
        try {
            populateViews();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    
        return view;
    }
    
    private PlaceDetails getPlaceDetails(String placeId){
        final PlaceDetails[] placeDetails = new PlaceDetails[1];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiParams.PLACES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFetch retroFetch = retrofit.create(RetroFetch.class);
        Call<PlaceDetailResult> call = retroFetch.getPlaceDetails(ApiParams.PLACE_API_KEY, placeId);
        call.enqueue(new Callback<PlaceDetailResult>() {
            @Override
            public void onResponse(Call<PlaceDetailResult> call, Response<PlaceDetailResult> response) {
                if(response.isSuccessful() && !(response == null)){
                    PlaceDetailResult result = response.body();
                    placeDetails[0] = result.getResult();
                }
                else {
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_LONG).show();
                }
            }
    
            @Override
            public void onFailure(Call<PlaceDetailResult> call, Throwable t) {
    
                Toast.makeText(getContext(), "failed to connect", Toast.LENGTH_LONG).show();
            }
        });
        
        PlaceDetails details =placeDetails[0];
        return details;
    }
    
    
    
    private void populateViews() throws MalformedURLException {
        PlaceDetails placeDetails = getPlaceDetails(nPlaceItem.getPlaceId());
        Glide.with(getContext()).load(Globals.getLink(nPlaceItem.getPhotos()[0]))
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
        
        if(placeDetails != null){
            switch(placeDetails.getPriceLevel()){
                case(1):
                    tvFair.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    break;
                case(2):
                    tvModerate.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    break;
                case(3):
                    tvPricey.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    break;
                case(4):
                    tvHighEnd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    break;
                default:
                    tvFree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            if((placeDetails.getFormattedPhoneNumber() != null)) {
                tvPhoneNumber.setText(placeDetails.getFormattedPhoneNumber());
            }else {
                tvPhoneNumber.setText("No Phone Number");
            }
            if(placeDetails.getBusinessWebsite() != null)
                tvWebsite.setText(placeDetails.getBusinessWebsite());
            else
                tvWebsite.setText("No website");
            initReviewsRecyclerView(placeDetails);
        }
        
        
        
    }
    
    private void initReviewsRecyclerView(PlaceDetails placeDetails) {
        ArrayList<PlaceReview> reviews = new ArrayList<>(Arrays.asList(placeDetails.getPlaceReview()));
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
        ratingBar = view.findViewById(R.id.rating_bar);
        tvPhoneNumber = view.findViewById(R.id.tv_phone_number);
        tvOpenMap = view.findViewById(R.id.tv_open_map);
        tvGoogleSite = view.findViewById(R.id.tv_google_website);
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