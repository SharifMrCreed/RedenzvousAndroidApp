package com.alle.san.restaurant.homeViews;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.FeedsRvAdapter;
import com.alle.san.restaurant.adapters.TopScrollRvAdapter;
import com.alle.san.restaurant.models.food.FoodItem;
import com.alle.san.restaurant.models.food.FoodResult;
import com.alle.san.restaurant.repo.RetroFetch;
import com.alle.san.restaurant.utilities.ApiParams;
import com.alle.san.restaurant.utilities.Globals;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.alle.san.restaurant.utilities.Globals.SEARCH_TERM;

public class FoodFragment extends Fragment {
    
    private final String TAG = "FeedFragment";
    private RecyclerView feedsRecyclerView;
    ProgressBar progressBar;
    
    static String results;
    String searchTerm = null;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            searchTerm = bundle.getString(SEARCH_TERM);
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foods_fragment, container, false);
        feedsRecyclerView = view.findViewById(R.id.feedsRv);
        RecyclerView topScroll = view.findViewById(R.id.top_scroll);
        progressBar = view.findViewById(R.id.feedsProgressBar);
        
        topScroll.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        topScroll.setAdapter(new TopScrollRvAdapter(Globals.foodItems, getContext()));
    
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String savedResults = preferences.getString("Result", null);
        
        if (searchTerm == null) {
            searchTerm = "burgers";
        }
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiParams.SPOON_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFetch retroFetch = retrofit.create(RetroFetch.class);
        Call<FoodResult> call = retroFetch.getMenuItems(ApiParams.SPOON_API_KEY, searchTerm, ApiParams.RESULT_NUMBER);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<FoodResult>() {
            @Override
            public void onResponse(Call<FoodResult> call, Response<FoodResult> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    FoodResult foodResult = response.body();
                    showResult((ArrayList<FoodItem>) foodResult.getMenuItems());
                }else{
    
                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                }
                
            }
            
            @Override
            public void onFailure(Call<FoodResult> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Failed to connect", Toast.LENGTH_LONG).show();
            }
        });
        
        
        return view;
    }
    
    private void showResult(ArrayList<FoodItem> listOfFoods) {
        
        ArrayList<FoodItem> displayList = new ArrayList<>();
        for (int i = 0; i < listOfFoods.size(); i++) {
            FoodItem item = listOfFoods.get(i);
            if (displayList.isEmpty()) {
                displayList.add(item);
            }
            if (!displayList.isEmpty()) {
                for (int j = 0; j < displayList.size(); j++) {
                    FoodItem foodItem = displayList.get(displayList.size() - 1);
                    if (!foodItem.getRestaurantName().equals(item.getRestaurantName())) {
                        displayList.add(item);
                    }
                }
            }
            
        }
        feedsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        feedsRecyclerView.setAdapter(new FeedsRvAdapter(displayList, getContext()));
        
    }
}
