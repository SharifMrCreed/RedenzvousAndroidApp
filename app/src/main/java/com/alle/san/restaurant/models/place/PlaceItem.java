package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class PlaceItem implements Parcelable {
    
    String name;
    @SerializedName("place_id")
    String placeId;
    float rating;
    ArrayList<PlacePhoto> photos;
    PlaceLocation location;
    @SerializedName("user_ratings_total")
    int totalRating;
    String vicinity;
    
    public PlaceItem() {
    }
    
    
    protected PlaceItem(Parcel in) {
        name = in.readString();
        placeId = in.readString();
        rating = in.readFloat();
        photos = in.createTypedArrayList(PlacePhoto.CREATOR);
        location = in.readParcelable(PlaceLocation.class.getClassLoader());
        totalRating = in.readInt();
        vicinity = in.readString();
    }
    
    public static final Creator<PlaceItem> CREATOR = new Creator<PlaceItem>() {
        @Override
        public PlaceItem createFromParcel(Parcel in) {
            return new PlaceItem(in);
        }
        
        @Override
        public PlaceItem[] newArray(int size) {
            return new PlaceItem[size];
        }
    };
    
    @Override
    public String toString() {
        return "PlaceItem{" +
                "name='" + name + '\'' +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", photos=" + photos +
                ", location=" + location +
                ", totalRating=" + totalRating +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
    
    public ArrayList<PlacePhoto> getPhotos() {
        return photos;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlaceId() {
        return placeId;
    }
    
    public float getRating() {
        return rating;
    }
    
    public int getTotalRating() {
        return totalRating;
    }
    
    public String getVicinity() {
        return vicinity;
    }
    
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(name);
        dest.writeString(placeId);
        dest.writeFloat(rating);
        dest.writeTypedList(photos);
        dest.writeParcelable(location, flags);
        dest.writeInt(totalRating);
        dest.writeString(vicinity);
    }
}
