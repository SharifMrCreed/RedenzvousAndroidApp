package com.alle.san.restaurant.models.wines;

import java.util.List;

public class WinesResult {
    private List<WineItem> recommendedWines;
    private int totalFound;
    
    public WinesResult(List<WineItem> recommendedWines, int totalFound) {
        this.recommendedWines = recommendedWines;
        this.totalFound = totalFound;
    }
    
    public List<WineItem> getRecommendedWines() {
        return recommendedWines;
    }
    
    public int getTotalFound() {
        return totalFound;
    }
}
