package com.alle.san.restaurant.models.place;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlaceSearchResult {
    @SerializedName("next_page_token")
    String pageToken;
    @SerializedName("results")
    ArrayList<PlaceItem> placeResults;
    
    public PlaceSearchResult(String pageToken, ArrayList<PlaceItem> placeResults) {
        this.pageToken = pageToken;
        this.placeResults = placeResults;
    }
    
    public String getPageToken() {
        return pageToken;
    }
    
    public List<PlaceItem> getPlaceResults() {
        return placeResults;
    }
}
