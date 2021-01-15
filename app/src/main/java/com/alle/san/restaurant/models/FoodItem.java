package com.alle.san.restaurant.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    String id;
    String name;
    String restaurantName;
    String servingSize;
    String image;
    
    protected FoodItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        restaurantName = in.readString();
        servingSize = in.readString();
        image = in.readString();
    }
    
    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }
        
        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
    
    @Override
    public String toString() {
        return "\n {" +
                "id : '" + id + '\'' +
                ", name : '" + name + '\'' +
                ", restaurantName : '" + restaurantName + '\'' +
                ", servingSize : '" + servingSize + '\'' +
                ", image : '" + image + '\'' +
                '}';
    }

    public FoodItem() {
    }

    public FoodItem(String id, String name, String restaurantName, String servingSize, String image) {
        this.id = id;
        this.name = name;
        this.restaurantName = restaurantName;
        this.servingSize = servingSize;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(restaurantName);
        dest.writeString(servingSize);
        dest.writeString(image);
    }
}
