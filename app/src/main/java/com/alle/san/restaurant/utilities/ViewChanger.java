package com.alle.san.restaurant.utilities;

import com.alle.san.restaurant.models.FoodItem;
import com.alle.san.restaurant.models.PlaceModel;

import java.util.ArrayList;

public interface ViewChanger {
    void onSignInButtonPressed();
    void onSignUpButtonPressed();
    void openFragmentCalled(String fragmentTag);
    void onTopButtonClick(String name);
    void onFoodItemClick(FoodItem food, ArrayList<FoodItem> foodItems);
    void onPlaceItemClick(PlaceModel place, ArrayList<PlaceModel> places);

}
