package com.alle.san.restaurant.models;

public class AdModel {
    private String name;
    private String snap;

    public AdModel() {
    }

    public AdModel(String name, String snap) {
        this.name = name;
        this.snap = snap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSnap() {
        return snap;
    }

    public void setSnap(String snap) {
        this.snap = snap;
    }
}
