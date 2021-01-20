package com.alle.san.restaurant.utilities;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.PlaceModel;

import java.util.ArrayList;

public class PlaceUtil {

    public static ArrayList<PlaceModel> getPlaces(){

        ArrayList<PlaceModel> places = new ArrayList<>();

        String oneLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.aa).toString();
        String twoLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.ab).toString();
        String threeLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.ac).toString();
        String fourLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.ad).toString();
        String fiveLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.ae).toString();
        String sixLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.af).toString();
    
        int a = R.drawable.aa;
        int b = R.drawable.ab;
        int c = R.drawable.ac;
        int d = R.drawable.ad;
        int e = R.drawable.ae;
        int f = R.drawable.af;
        
        PlaceModel placeOne = new PlaceModel("Arcadia", "Location", oneLocale, a);
        PlaceModel placeTwo = new PlaceModel("Atlantis", "Location", twoLocale, b);
        PlaceModel placeThree = new PlaceModel("Tantalus", "Location", threeLocale, e);
        PlaceModel placeFour = new PlaceModel("Chaldea", "Location", fourLocale, d);
        PlaceModel placeFive = new PlaceModel("Olympus", "Location", fiveLocale, e);
        PlaceModel placeSix = new PlaceModel("Kalybdie", "Location", sixLocale, f);

        for (int i = 0; i<5; i++){
            places.add(placeOne);
            places.add(placeTwo);
            places.add(placeThree);
            places.add(placeFour);
            places.add(placeFive);
            places.add(placeSix);
        }

        return places;
    }



}
