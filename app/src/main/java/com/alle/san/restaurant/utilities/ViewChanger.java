package com.alle.san.restaurant.utilities;

import android.widget.ImageView;
import android.widget.TextView;

import com.alle.san.restaurant.models.food.FoodItem;
import com.alle.san.restaurant.models.place.PlaceItem;

import java.util.ArrayList;

public interface ViewChanger {
    void onSignInButtonPressed();
    void onSignUpButtonPressed();
    void openFragmentCalled(String fragmentTag);
    void onTopButtonClick(String name);
    void onFoodItemClick(FoodItem food, ArrayList<FoodItem> foodItems, ImageView pic, TextView foodName, TextView placeName);
    void onPlaceItemClick(PlaceItem place, ArrayList<PlaceItem> places, ImageView pic, TextView placeName);

}
