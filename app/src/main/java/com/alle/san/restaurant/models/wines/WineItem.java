package com.alle.san.restaurant.models.wines;

public class WineItem {
    private int id;
    private String title;
    private float averageRating;
    private String description;
    private String imageUrl;
    private String link;
    private String price;
    private float ratingCount;
    private float score;
    
    public WineItem(int id, String title, float averageRating, String description, String imageUrl, String link, String price, float ratingCount, float score) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.averageRating = averageRating;
        this.imageUrl = imageUrl;
        this.link = link;
        this.price = price;
        this.ratingCount = ratingCount;
        this.score = score;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public float getAverageRating() {
        return averageRating;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public String getLink() {
        return link;
    }
    
    public String getPrice() {
        return price;
    }
    
    public float getRatingCount() {
        return ratingCount;
    }
    
    public float getScore() {
        return score;
    }
}
