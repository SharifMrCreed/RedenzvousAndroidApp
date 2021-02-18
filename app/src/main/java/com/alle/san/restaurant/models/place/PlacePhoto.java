package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PlacePhoto implements Parcelable {
    int height;
    int width;
    @SerializedName("photo_reference")
    String photoReference;
    
    public PlacePhoto(int height, int width, String photoReference) {
        this.height = height;
        this.width = width;
        this.photoReference = photoReference;
        
    }
    
    protected PlacePhoto(Parcel in) {
        height = in.readInt();
        width = in.readInt();
        photoReference = in.readString();
    }
    
    public static final Creator<PlacePhoto> CREATOR = new Creator<PlacePhoto>() {
        @Override
        public PlacePhoto createFromParcel(Parcel in) {
            return new PlacePhoto(in);
        }
        
        @Override
        public PlacePhoto[] newArray(int size) {
            return new PlacePhoto[size];
        }
    };
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public String getPhotoReference() {
        return photoReference;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeString(photoReference);
    }
}
