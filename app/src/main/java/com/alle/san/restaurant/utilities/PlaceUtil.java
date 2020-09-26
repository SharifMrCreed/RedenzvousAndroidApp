package com.alle.san.restaurant.utilities;

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

        PlaceModel placeOne = new PlaceModel("Arcadia", "Location", oneLocale);
        PlaceModel placeTwo = new PlaceModel("Atlantis", "Location", twoLocale);
        PlaceModel placeThree = new PlaceModel("Tantalus", "Location", threeLocale);
        PlaceModel placeFour = new PlaceModel("Chaldea", "Location", fourLocale);
        PlaceModel placeFive = new PlaceModel("Olympus", "Location", fiveLocale);
        PlaceModel placeSix = new PlaceModel("Kalybdie", "Location", sixLocale);

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
