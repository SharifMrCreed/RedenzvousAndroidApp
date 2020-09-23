package com.alle.san.restaurant.models;

public class FoodItem {
    String id;
    String name;
    String restaurantName;
    String servingSize;
    String image;

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
}
