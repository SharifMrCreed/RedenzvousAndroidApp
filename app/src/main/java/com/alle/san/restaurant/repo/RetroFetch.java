package com.alle.san.restaurant.repo;

import android.media.Image;

import com.alle.san.restaurant.models.food.FoodResult;
import com.alle.san.restaurant.models.place.PlaceDetailResult;
import com.alle.san.restaurant.models.place.PlaceSearchResult;
import com.alle.san.restaurant.models.wines.WinesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroFetch {
    @GET("menuItems/search")
    Call<FoodResult> getMenuItems(
            @Query("apiKey") String apiKey,
            @Query("query") String searchTerm,
            @Query("number") int totalResults
    );
    @GET("wine/recommendation")
    Call<WinesResult> getWines(
            @Query("apiKey") String apiKey,
            @Query("wine") String searchTerm,
            @Query("number") int totalResults
    );
    
    @GET("nearbysearch/json")
    Call<PlaceSearchResult> getPlaces(
            @Query("key") String key,
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type
    );
    
    @GET("details/json")
    Call<PlaceDetailResult> getPlaceDetails(
            @Query("key") String key,
            @Query("place_id") String placeId
    );
    @GET("nearbysearch/json")
    Call<PlaceSearchResult> getMorePlaces(
            @Query("pagetoken") String pageToken,
            @Query("key") String key
    );
    
}
