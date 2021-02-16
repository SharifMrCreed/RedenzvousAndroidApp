package com.alle.san.restaurant.utilities;

import android.net.Uri;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.AdModel;

import java.util.ArrayList;

public class AdUtil {

    public static ArrayList<AdModel> getPlaces(){

        ArrayList<AdModel> ads = new ArrayList<>();

        String oneLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adb).toString();
        String twoLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adc).toString();
        String threeLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.add).toString();
        String fourLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adda).toString();
        String fiveLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addb).toString();
        String sixLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addd).toString();
        String sevLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adde).toString();
        String eigLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addh).toString();
        String nineLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addi).toString();
        String tenLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addj).toString();
        String elevLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addk).toString();
        String twelLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.addl).toString();
        String thirtLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adc).toString();
        String fortLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.ade).toString();
        String fiftLocale = Uri.parse("android.resource://com.alle.san.restaurant/" + R.drawable.adk).toString();

        AdModel placeOne = new AdModel("Food Fest", oneLocale);
        AdModel placeTwo = new AdModel("Food Fest", twoLocale);
        AdModel placeThree = new AdModel("Food Fest", threeLocale);
        AdModel placeFour = new AdModel("Food Fest", fourLocale);
        AdModel placeFive = new AdModel("Food Fest", fiveLocale);
        AdModel placeSix = new AdModel("Food Fest", sixLocale);
        AdModel placeSev = new AdModel("Food Fest", sevLocale);
        AdModel placeEig = new AdModel("Food Fest", eigLocale);
        AdModel placeNine = new AdModel("Food Fest", nineLocale);
        AdModel placeTen = new AdModel("Food Fest", tenLocale);
        AdModel placeElev = new AdModel("Food Fest", elevLocale);
        AdModel placeTwel = new AdModel("Food Fest",twelLocale);
        AdModel placeThirt = new AdModel("Food Fest",thirtLocale);
        AdModel placeFort = new AdModel("Food Fest", fortLocale);
        AdModel placeFift = new AdModel("Food Fest", fiftLocale);

        for (int i = 0; i<3; i++){
            ads.add(placeOne);
            ads.add(placeTwo);
            ads.add(placeThree);
            ads.add(placeFour);
            ads.add(placeFive);
            ads.add(placeSix);
            ads.add(placeSev);
            ads.add(placeEig);
            ads.add(placeNine);
            ads.add(placeTen);
            ads.add(placeElev);
            ads.add(placeTwel);
            ads.add(placeThirt);
            ads.add(placeFort);
            ads.add(placeFift);
        }

        return ads;
    }



}
