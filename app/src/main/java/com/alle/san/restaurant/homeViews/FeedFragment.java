package com.alle.san.restaurant.homeViews;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.FeedsRvAdapter;
import com.alle.san.restaurant.models.FoodItem;
import com.alle.san.restaurant.utilities.ParseMenuItem;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FeedFragment extends Fragment {

    private static final String TAG = "FeedFragment";
    private static RecyclerView feedsRecyclerView;
    static ProgressBar progressBar;

    static ArrayList<FoodItem> listOfFoods = new ArrayList<>();

   static String results;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feeds_fragment,container,false);
        feedsRecyclerView = view.findViewById(R.id.feedsRv);
        progressBar = view.findViewById(R.id.feedsProgressBar);

        ApiUtil apiUtil = new ApiUtil();
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        try {
            URL url = apiUtil.searchFor("beef");
            String savedResults = preferences.getString("Result", null);
            if (savedResults == null){
                apiUtil.execute(url);
                Log.d(TAG, "onCreateView:  Results were null");
            }else{
                showResult(savedResults);
            }

            Log.d(TAG, "onCreateView:  "+ listOfFoods.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return view;
    }

    public class ApiUtil extends AsyncTask<URL, Void, String> {
        private static final String TAG = "ApiUtil";

        public static final String BASE_URL = "https://api.spoonacular.com/food/menuItems/search";
        public static final String API_KEY ="0d3cecb0a6604900b374d10acf83c87b";
        public static final String QUERY_PARAMETER = "query";
        public static final String KEY = "apiKey";
        public static final String NUMBER = "number";
        public static final String TOTAL_RESULTS = "500";

        public URL searchFor(String searchQuery) throws MalformedURLException {

            Uri uri = Uri.parse(BASE_URL).buildUpon()
                         .appendQueryParameter(KEY, API_KEY)
                         .appendQueryParameter(QUERY_PARAMETER, searchQuery)
                         .appendQueryParameter(NUMBER, TOTAL_RESULTS).build();

            URL url= new URL(uri.toString());

            return  url;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            results = "";
            HttpURLConnection connection = null;
            InputStream stream;
            Scanner scanner;
            URL searchUrl = urls[0];


            try {
                connection = (HttpURLConnection) searchUrl.openConnection();
                stream = connection.getInputStream();
                scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                while (scanner.hasNext()){
                    results = results + scanner.next();
                }
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: the Url "+ searchUrl.toString() +" " +e.toString());
            }finally {
                if (connection!= null){
                    connection.disconnect();
                }
            }
            return results;
        }

        @Override
        protected void onPostExecute(String jsonResult) {

            preferences.getString("Results", jsonResult);
            Log.d(TAG, "onPostExecute: \n" + jsonResult);
            progressBar.setVisibility(View.GONE);

            showResult(jsonResult);

        }
    }

    private void showResult(String jsonResult) {
        try {
            listOfFoods = ParseMenuItem.menuItems(jsonResult);
            ArrayList<FoodItem> displayList = new ArrayList<>();
            for(int i=0; i<listOfFoods.size(); i++){
                FoodItem item = listOfFoods.get(i);
                if (displayList.isEmpty()){
                    displayList.add(item);
                }
                if (!displayList.isEmpty()){
                    for (int j=0; j<displayList.size(); j++){
                        FoodItem foodItem = displayList.get(displayList.size()-1);
                        if(!foodItem.getRestaurantName().equals(item.getRestaurantName())){
                            displayList.add(item);
                        }
                    }
                }

            }
            feedsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            feedsRecyclerView.setAdapter(new FeedsRvAdapter(displayList, getContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
