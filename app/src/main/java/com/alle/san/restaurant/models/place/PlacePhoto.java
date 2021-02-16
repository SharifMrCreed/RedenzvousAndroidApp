package com.alle.san.restaurant.models.place;

import com.google.gson.annotations.SerializedName;

public class PlacePhoto {
    int height;
    int width;
    @SerializedName("photo_reference")
    String photoReference;
    
    public PlacePhoto(int height, int width, String photoReference) {
        this.height = height;
        this.width = width;
        this.photoReference = photoReference;
        
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public String getPhotoReference() {
        return photoReference;
    }
}
