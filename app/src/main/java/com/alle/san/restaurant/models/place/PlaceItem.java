package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class PlaceItem implements Parcelable {
    
    String name;
    @SerializedName("place_id")
    String placeId;
    float rating;
    PlacePhoto[] photos;
    String[] types;
    @SerializedName("user_ratings_total")
    int totalRating;
    String vicinity;
    
    public PlaceItem() {
    }
    
    public PlaceItem(String name, PlacePhoto[] photos, String placeId, float rating, String[] types, int totalRating, String vicinity) {
        this.name = name;
        this.photos = photos;
        this.placeId = placeId;
        this.rating = rating;
        this.types = types;
        this.totalRating = totalRating;
        this.vicinity = vicinity;
    }
    
    @Override
    public String toString() {
        return "\nPlaceItem{" +
                "name='" + name + '\'' +
                ", placeId='" + placeId + '\'' +
                ", rating=" + rating +
                ", photos=" + Arrays.toString(photos) +
                ", types=" + Arrays.toString(types) +
                ", totalRating=" + totalRating +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }
    
    protected PlaceItem(Parcel in) {
        name = in.readString();
        placeId = in.readString();
        rating = in.readFloat();
        types = in.createStringArray();
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
    
    public PlacePhoto[] getPhotos() {
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
    
    public String[] getTypes() {
        return types;
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
        dest.writeStringArray(types);
        dest.writeInt(totalRating);
        dest.writeString(vicinity);
    }
}
