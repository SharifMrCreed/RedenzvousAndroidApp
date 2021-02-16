package com.alle.san.restaurant.models.place;

import com.google.gson.annotations.SerializedName;

public class PlaceDetails {
    @SerializedName("formatted_address")
    String formattedAddress;
    @SerializedName("formatted_phone_number")
    String formattedPhoneNumber;
    @SerializedName("location")
    PlaceLocation placeLocation;
    @SerializedName("international_phone_number")
    String intPhoneNumber;
    @SerializedName("price_level")
    int priceLevel;
    @SerializedName("name")
    String placeName;
    @SerializedName("place_id")
    String placeId;
    @SerializedName("rating")
    float placeRating;
    @SerializedName("url")
    String googleWebsite;
    @SerializedName("website")
    String businessWebsite;
    String vicinity;
    @SerializedName("reviews")
    PlaceReview[] placeReview;
    String[] types;
    
    public PlaceDetails() {
    }
    
    public String getFormattedAddress() {
        return formattedAddress;
    }
    
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }
    
    public PlaceLocation getPlaceLocation() {
        return placeLocation;
    }
    
    public String getIntPhoneNumber() {
        return intPhoneNumber;
    }
    
    public int getPriceLevel() {
        return priceLevel;
    }
    
    public String getPlaceName() {
        return placeName;
    }
    
    public String getPlaceId() {
        return placeId;
    }
    
    public float getPlaceRating() {
        return placeRating;
    }
    
    public String getGoogleWebsite() {
        return googleWebsite;
    }
    
    public String getBusinessWebsite() {
        return businessWebsite;
    }
    
    public String getVicinity() {
        return vicinity;
    }
    
    public PlaceReview[] getPlaceReview() {
        return placeReview;
    }
    
    public String[] getTypes() {
        return types;
    }
}
