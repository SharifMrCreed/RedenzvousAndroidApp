package com.alle.san.restaurant.models.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PlaceReview  implements Parcelable {
    @SerializedName("author_name")
    String authorName;
    @SerializedName("profile_photo_url")
    String profileUrl;
    float rating;
    @SerializedName("relative_time_description")
    String relativeTime;
    @SerializedName("text")
    String review;
    int time;
    
    public PlaceReview() {
    }
    
    protected PlaceReview(Parcel in) {
        authorName = in.readString();
        profileUrl = in.readString();
        rating = in.readFloat();
        relativeTime = in.readString();
        review = in.readString();
        time = in.readInt();
    }
    
    public static final Creator<PlaceReview> CREATOR = new Creator<PlaceReview>() {
        @Override
        public PlaceReview createFromParcel(Parcel in) {
            return new PlaceReview(in);
        }
        
        @Override
        public PlaceReview[] newArray(int size) {
            return new PlaceReview[size];
        }
    };
    
    public String getAuthorName() {
        return authorName;
    }
    
    public String getProfileUrl() {
        return profileUrl;
    }
    
    public float getRating() {
        return rating;
    }
    
    public String getRelativeTime() {
        return relativeTime;
    }
    
    public String getReview() {
        return review;
    }
    
    public int getTime() {
        return time;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(authorName);
        dest.writeString(profileUrl);
        dest.writeFloat(rating);
        dest.writeString(relativeTime);
        dest.writeString(review);
        dest.writeInt(time);
    }
}
