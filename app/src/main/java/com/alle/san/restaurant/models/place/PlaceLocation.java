package com.alle.san.restaurant.models.place;

import com.google.gson.annotations.SerializedName;

public class PlaceLocation {
    
    @SerializedName("lat")
    String placeLatitude;
    @SerializedName("lng")
    String placeLongitude;
    
    public PlaceLocation() {
    }
    
    public String getPlaceLatitude() {
        return placeLatitude;
    }
    
    public String getPlaceLongitude() {
        return placeLongitude;
    }
}
