package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlaceDetails implements Parcelable {
    @SerializedName("international_phone_number")
    String intPhoneNumber;
    @SerializedName("price_level")
    int priceLevel;
    @SerializedName("website")
    String businessWebsite;
    @SerializedName("reviews")
    ArrayList<PlaceReview> placeReview;
    
    public PlaceDetails() {
    }
    
    protected PlaceDetails(Parcel in) {
        intPhoneNumber = in.readString();
        priceLevel = in.readInt();
        businessWebsite = in.readString();
        placeReview = in.createTypedArrayList(PlaceReview.CREATOR);
    }
    
    public static final Creator<PlaceDetails> CREATOR = new Creator<PlaceDetails>() {
        @Override
        public PlaceDetails createFromParcel(Parcel in) {
            return new PlaceDetails(in);
        }
        
        @Override
        public PlaceDetails[] newArray(int size) {
            return new PlaceDetails[size];
        }
    };
    
    public String getIntPhoneNumber() {
        return intPhoneNumber;
    }
    
    public int getPriceLevel() {
        return priceLevel;
    }
    
    
    public String getBusinessWebsite() {
        return businessWebsite;
    }
    
    public ArrayList<PlaceReview> getPlaceReview() {
        return placeReview;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(intPhoneNumber);
        dest.writeInt(priceLevel);
        dest.writeString(businessWebsite);
        dest.writeTypedList(placeReview);
    }
}
