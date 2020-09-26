package com.alle.san.restaurant.models;

public class PlaceModel {

    private String name;
    private String locale;
    private String localePics;

    public PlaceModel() {
    }

    public PlaceModel(String name, String locale, String localePics) {
        this.name = name;
        this.locale = locale;
        this.localePics = localePics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocalePics() {
        return localePics;
    }

    public void setLocalePics(String localePics) {
        this.localePics = localePics;
    }
}
