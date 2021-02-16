package com.alle.san.restaurant.homeViews;

import android.os.Bundle;
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
import com.alle.san.restaurant.adapters.PlaceItemAdapter;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.models.place.PlaceSearchResult;
import com.alle.san.restaurant.repo.RetroFetch;
import com.alle.san.restaurant.utilities.Globals;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceFragment extends Fragment {

    RecyclerView rvPlaces;
    ProgressBar progressBar;
    private Retrofit nRetrofit;
    private RetroFetch nRetroFetch;
    private PlaceSearchResult nPlaceSearchResult;
    private PlaceSearchResult nPlaceSearchResult2;
    private PlaceItemAdapter nAdapter;
    private LinearLayoutManager nManager;
    
    ArrayList<PlaceItem> nPlaceItemList = new ArrayList<>();
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places_fragment, container, false);
        rvPlaces = view.findViewById(R.id.placesRv);
        progressBar = view.findViewById(R.id.progressBar);
        
        nRetrofit = new Retrofit.Builder()
                .baseUrl(Globals.PLACES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nRetroFetch = nRetrofit.create(RetroFetch.class);
        
        fetchData(Globals.TYPE);
        loadMore();
        return view;
    }
    
    private void fetchData(String type) {
    
        Call<PlaceSearchResult> call = nRetroFetch.getPlaces(Globals.PLACE_API_KEY, Globals.LOCATION, Globals.RADIUS, type);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<PlaceSearchResult>() {
            @Override
            public void onResponse(Call<PlaceSearchResult> call, Response<PlaceSearchResult> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    nPlaceSearchResult = response.body();
                    ArrayList<PlaceItem> anotherList = filteredList((ArrayList<PlaceItem>) nPlaceSearchResult.getPlaceResults());
                    nPlaceItemList.addAll(anotherList);
                    
                    nAdapter = new PlaceItemAdapter(nPlaceItemList, getContext());
                    nManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    initPlacesRv();
                } else {
                
                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            
            }
        
            @Override
            public void onFailure(Call<PlaceSearchResult> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Failed to connect", Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private void loadMore() {
        rvPlaces.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = nManager.getChildCount();
                int totalItemCount = nManager.getItemCount();
                int scrolledItemCount = nManager.findFirstVisibleItemPosition();
                if (nPlaceSearchResult.getPageToken() != null){
                    if ((visibleItemCount + scrolledItemCount)>= totalItemCount) {
                        Call<PlaceSearchResult> call = nRetroFetch.getMorePlaces(nPlaceSearchResult.getPageToken(), Globals.PLACE_API_KEY);
                        call.enqueue(new Callback<PlaceSearchResult>() {
                            @Override
                            public void onResponse(Call<PlaceSearchResult> call, Response<PlaceSearchResult> response) {
                                if (response.isSuccessful()) {
                                    nPlaceSearchResult2 = response.body();
                                    ArrayList<PlaceItem> placeItems = filteredList((ArrayList<PlaceItem>) nPlaceSearchResult2.getPlaceResults());
                                    nAdapter.updateItems(placeItems);
                                    
                                    
                                } else {
                                    Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                            
                            @Override
                            public void onFailure(Call<PlaceSearchResult> call, Throwable t) {
                                Toast.makeText(getContext(), "Could Not Connect", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else{
                    fetchData("lodging");
                }
            }
        });
    }
    
    
    private ArrayList<PlaceItem> filteredList(ArrayList<PlaceItem> placeItemArrayList){
        ArrayList<PlaceItem> someList = new ArrayList<>();
        for (PlaceItem item: placeItemArrayList){
            if (item.getPhotos() != null){
                someList.add(item);
            }
        }
        return someList;
    }

    private void initPlacesRv() {
    
        rvPlaces.setAdapter(nAdapter);
        rvPlaces.setLayoutManager(nManager);
    }
}
