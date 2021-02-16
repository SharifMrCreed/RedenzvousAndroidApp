package com.alle.san.restaurant.models.place;

import com.google.gson.annotations.SerializedName;

public class PlaceReview {
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
}
