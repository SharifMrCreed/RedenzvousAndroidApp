package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PlaceLocation implements Parcelable {
    
    @SerializedName("lat")
    String placeLatitude;
    @SerializedName("lng")
    String placeLongitude;
    
    public PlaceLocation() {
    }
    
    protected PlaceLocation(Parcel in) {
        placeLatitude = in.readString();
        placeLongitude = in.readString();
    }
    
    public static final Creator<PlaceLocation> CREATOR = new Creator<PlaceLocation>() {
        @Override
        public PlaceLocation createFromParcel(Parcel in) {
            return new PlaceLocation(in);
        }
        
        @Override
        public PlaceLocation[] newArray(int size) {
            return new PlaceLocation[size];
        }
    };
    
    @Override
    public String toString() {
        return "\nPlaceLocation{" +
                "placeLatitude='" + placeLatitude + '\'' +
                ", placeLongitude='" + placeLongitude + '\'' +
                '}';
    }
    
    public String getPlaceLatitude() {
        return placeLatitude;
    }
    
    public String getPlaceLongitude() {
        return placeLongitude;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(placeLatitude);
        dest.writeString(placeLongitude);
    }
}
