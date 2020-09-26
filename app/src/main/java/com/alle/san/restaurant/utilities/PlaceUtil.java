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

        PlaceModel placeOne = new PlaceModel("Place One", "Location", oneLocale);
        PlaceModel placeTwo = new PlaceModel("Place One", "Location", twoLocale);
        PlaceModel placeThree = new PlaceModel("Place One", "Location", threeLocale);
        PlaceModel placeFour = new PlaceModel("Place One", "Location", fourLocale);
        PlaceModel placeFive = new PlaceModel("Place One", "Location", fiveLocale);
        PlaceModel placeSix = new PlaceModel("Place One", "Location", sixLocale);

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
