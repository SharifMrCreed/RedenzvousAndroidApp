package com.alle.san.restaurant.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceModel implements Parcelable {

    private String name;
    private String locale;
    private String localePics;
    int drawable;

    public PlaceModel() {
    }

    public PlaceModel(String name, String locale, String localePics, int drawable) {
        this.name = name;
        this.locale = locale;
        this.localePics = localePics;
        this.drawable = drawable;
    }
    
    protected PlaceModel(Parcel in) {
        name = in.readString();
        locale = in.readString();
        localePics = in.readString();
        drawable = in.readInt();
    }
    
    public static final Creator<PlaceModel> CREATOR = new Creator<PlaceModel>() {
        @Override
        public PlaceModel createFromParcel(Parcel in) {
            return new PlaceModel(in);
        }
        
        @Override
        public PlaceModel[] newArray(int size) {
            return new PlaceModel[size];
        }
    };
    
    public int getDrawable() {
        return drawable;
    }
    
    public void setDrawable(int drawable) {
        this.drawable = drawable;
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
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(name);
        dest.writeString(locale);
        dest.writeString(localePics);
        dest.writeInt(drawable);
    }
}
