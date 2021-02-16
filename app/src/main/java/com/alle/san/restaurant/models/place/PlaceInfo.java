package com.alle.san.restaurant.models.place;

public class PlaceInfo {
    PlaceItem placeItem;
    PlaceDetails placeDetails;
    
    public PlaceInfo(PlaceItem placeItem, PlaceDetails placeDetails) {
        this.placeItem = placeItem;
        this.placeDetails = placeDetails;
    }
    
    public PlaceInfo() {
    }
    
    public PlaceItem getPlaceItem() {
        return placeItem;
    }
    
    public PlaceDetails getPlaceDetails() {
        return placeDetails;
    }
}
