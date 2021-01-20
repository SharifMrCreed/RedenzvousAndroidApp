package com.alle.san.restaurant.utilities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.alle.san.restaurant.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Globals {
    // fragments
    public static final String ACCOUNTS_FRAGMENT_TAG = "Accounts Fragment";
    public static final String SIGN_IN_FRAGMENT_TAG = "Sign In Fragment";
    public static final String SIGN_UP_FRAGMENT_TAG = "Sign Up Fragment";
    public static final String HOME_FRAGMENT_TAG = "Home Fragment";
    public static final String FAVOURITES_FRAGMENT_TAG = "Favourites Fragment";
    public static final String CHATS_FRAGMENT_TAG = "Chats Fragment";
    public static final String FEED_FRAGMENT_TAG = "Feed Fragment";
    public static final String PARTY_FRAGMENT_TAG = "Party Fragment";
    public static final String PICKS_FRAGMENT_TAG = "Picks Fragment";
    public static final String PROFILE_FRAGMENT_TAG = "Profile Fragment";
    public static final String FOOD_ITEM_FRAGMENT_TAG = "Food Item Fragment";
    public static final String PLACE_ITEM_FRAGMENT_TAG = "Place Item Fragment";
    public static final String SEARCH_TERM = "Search term";
    public static final String FOOD_ITEM = "Food item";
    public static final String PLACE_ITEM = "Place item";
    public static final String PLACE_ITEMS = "Place items";
    public static final String FOOD_ITEMS = "Food items";
    
    public static String[] foodItems = new String[]{
            "Pizza", "Fries", "Burger", "Pasta", "Fries", "Beef", "Ice cream", "Salad",
            "Chicken", "Rice", "Milk Shake", "Donuts", "Cocktails", "Wines", "Beer", "Juice"
    };
    public static Bitmap getBitmapAt(URL url){
        final Bitmap[] bitmap = {null};
            AsyncTask<URL, ViewChanger, Bitmap> task = new AsyncTask<URL, ViewChanger, Bitmap>(){
    
                @Override
                protected Bitmap doInBackground(URL... urls) {
                    Bitmap image = null;
                    try {
                        URL url = urls[0];
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        
                        image = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return image;
                }
    
                @Override
                protected void onPostExecute(Bitmap result) {
                    super.onPostExecute(result);
                    bitmap[0] = result;
                }
            };
            task.execute(url);
    
        return bitmap[0];
    }

    // fire base nodes
    public static final String FIREBASE_USERS_NODE = "users";
    public static final String FIREBASE_CHAT_NODE = "chat_room";

    //firebase fields
    public static final String FIREBASE_NAME_FIELD = "name";
    public static final String FIREBASE_EMAIL_FIELD = "email";
    public static final String FIREBASE_ID_FIELD = "u_id";
    public static final String FIREBASE_PHONE_FIELD = "phone";
    public static final String FIREBASE_PROFILE_IMAGE_FIELD = "profile_image";
    public static final String FIREBASE_CREATOR_ID_FIELD = "creator_id";
    
    

}
