package com.alle.san.restaurant.utilities;

import android.view.MenuItem;

import com.alle.san.restaurant.models.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseMenuItem {

    public static final String MENU_ITEMS = "menuItems";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String RESTAURANT_CHAIN = "restaurantChain";
    public static final String SERVING_SIZE = "servingSize";
    public static final String IMAGE_URL = "image";

    public static ArrayList<FoodItem> menuItems(String json) throws JSONException {

        ArrayList<FoodItem> menuItemArrayList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray foods = jsonObject.getJSONArray(MENU_ITEMS);
         for (int i = 0; i< foods.length(); i++){
             FoodItem foodItem = new FoodItem();
             JSONObject item =  foods.getJSONObject(i);
             String itemId = String.valueOf(item.get(ID)) ;
             if (itemId.equals(null)) { foodItem.setId("xxxx"); } else { foodItem.setId(itemId); }
             String itemName = (String) item.get(TITLE);
             if (itemName.equals(null)) { foodItem.setName(""); } else { foodItem.setName(itemName); }
             String itemRestaurant = (String) item.get(RESTAURANT_CHAIN);
             if (itemName.equals(null)) { foodItem.setRestaurantName(""); } else { foodItem.setRestaurantName(itemRestaurant); }
//             String itemServingSize= null; //= (String) item.get(SERVING_SIZE);
//             if (itemServingSize.equals(null)) { foodItem.setServingSize(""); } else { foodItem.setServingSize(itemServingSize); }
             String itemImageUrl = (String) item.get(IMAGE_URL);
             if (itemImageUrl.equals(null)) { foodItem.setImage(""); } else { foodItem.setImage(itemImageUrl); }

             menuItemArrayList.add(foodItem);

         }



        return menuItemArrayList;
    }
}
