package com.alle.san.restaurant.models.food;

import java.util.ArrayList;
import java.util.List;

public class FoodResult {
    private ArrayList<FoodItem> menuItems;
    private  int totalMenuItems;
    private String type;
    private int offset;
    private int number;
    
    public FoodResult(ArrayList<FoodItem> menuItems, int totalMenuItems, String type, int offset, int number) {
        this.menuItems = menuItems;
        this.totalMenuItems = totalMenuItems;
        this.type = type;
        this.offset = offset;
        this.number = number;
    }
    
    public List<FoodItem> getMenuItems() {
        return menuItems;
    }
    
    public int getTotalMenuItems() {
        return totalMenuItems;
    }
    
    public String getType() {
        return type;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getNumber() {
        return number;
    }
}
