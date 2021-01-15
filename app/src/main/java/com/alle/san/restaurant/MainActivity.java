package com.alle.san.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.alle.san.restaurant.homeViews.FoodItemFragment;
import com.alle.san.restaurant.homeViews.HomeFragment;
import com.alle.san.restaurant.homeViews.PlaceItemFragment;
import com.alle.san.restaurant.models.FoodItem;
import com.alle.san.restaurant.models.PlaceModel;
import com.alle.san.restaurant.profileViews.AccountFragment;
import com.alle.san.restaurant.profileViews.ProfileFragment;
import com.alle.san.restaurant.profileViews.SignInFragment;
import com.alle.san.restaurant.profileViews.SignUpFragment;
import com.alle.san.restaurant.utilities.Globals;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static com.alle.san.restaurant.utilities.Globals.ACCOUNTS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.CHATS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.FAVOURITES_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.HOME_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SEARCH_TERM;
import static com.alle.san.restaurant.utilities.Globals.SIGN_IN_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_UP_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements ViewChanger {

    BottomNavigationView bottomNavigationView;
    Toolbar toolBar;


    private int fragmentContainer;
    HomeFragment homeFragment;
    ChatsFragment chatsFragment;
    FavoritesFragment favoritesFragment;
    AccountFragment accountFragment;
    SignUpFragment signUpFragment;
    SignInFragment signInFragment;
    ProfileFragment profileFragment;
    private ActionBar supportActionBar;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        toolBar = findViewById(R.id.appToolBar);
        fragmentContainer = R.id.fragment_container;

        setSupportActionBar(toolBar);
        supportActionBar = getSupportActionBar();
        user = FirebaseAuth.getInstance().getCurrentUser();

        initBottomNavigationView();

        initFragment(new HomeFragment(), HOME_FRAGMENT_TAG);


    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {

                    Fragment selectedFragment = null;
                    String tag = null;
                    switch (item.getItemId()){
                        case(R.id.action_home):
                            selectedFragment = new HomeFragment();
                            tag = HOME_FRAGMENT_TAG;
                            showToolbar();
                            break;
                        case(R.id.action_chat):
                            if (chatsFragment == null){
                                chatsFragment = new ChatsFragment();
                            }
                            selectedFragment = chatsFragment;
                            tag = CHATS_FRAGMENT_TAG;
                            showToolbar();
                            break;
                        case(R.id.action_favourites):
                            if (favoritesFragment == null){
                                favoritesFragment = new FavoritesFragment();
                            }
                            selectedFragment = favoritesFragment;
                            tag = FAVOURITES_FRAGMENT_TAG;
                            showToolbar();
                            break;
                        case(R.id.action_profile):
                            hideToolBar();
                            if (user != null){
                                if (profileFragment == null){
                                    profileFragment = new ProfileFragment();
                                }
                                selectedFragment = profileFragment;
                                tag = PROFILE_FRAGMENT_TAG;
                            }else{
                                if (accountFragment == null) {
                                    accountFragment = new AccountFragment();
                                }
                                selectedFragment = accountFragment;
                                tag = ACCOUNTS_FRAGMENT_TAG;
                            }
                            break;

                    }
                    if (selectedFragment != null){
                        initFragment(selectedFragment, tag);

                    }
                    return true;
                });
    }
    private void showToolbar(){
        if (supportActionBar != null && (!supportActionBar.isShowing())){
            supportActionBar.show();
        }
    }

    private  void hideToolBar(){
        if (supportActionBar != null && (supportActionBar.isShowing())){
            supportActionBar.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                Bundle args = new Bundle();
                Toast.makeText(MainActivity.this, " Searching for " + search, Toast.LENGTH_LONG).show();
                args.putString(SEARCH_TERM, search);
                HomeFragment homeNew = new HomeFragment();
                homeNew.setArguments(args);
                hideSoftKeyboard();
                initFragment(homeNew, HOME_FRAGMENT_TAG);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search){


            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getVisibility() != View.GONE){
            switch (bottomNavigationView.getSelectedItemId()){
                case(R.id.action_home):
                    super.onBackPressed();
                    break;
                case(R.id.action_chat):
                    if (homeFragment == null){
                        homeFragment = new HomeFragment();
                    }
                    initFragment(homeFragment, HOME_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_home);
                    break;
                case(R.id.action_favourites):
                    if (chatsFragment == null){
                    chatsFragment = new ChatsFragment();
                    }
                    initFragment(chatsFragment, CHATS_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_chat);
                    break;
                case(R.id.action_profile):
                    if (favoritesFragment == null){
                        favoritesFragment = new FavoritesFragment();
                    }
                    initFragment(favoritesFragment, FAVOURITES_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_favourites);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    showToolbar();
                    break;

            }
        }else{
            if (user != null){
                if (profileFragment == null){
                    profileFragment = new ProfileFragment();
                }
                initFragment(profileFragment, PROFILE_FRAGMENT_TAG);
            }else{
                if (accountFragment == null){
                    accountFragment = new AccountFragment();
                }
                initFragment(accountFragment, ACCOUNTS_FRAGMENT_TAG);

            }
            bottomNavigationView.setVisibility(View.VISIBLE);

        }
    }

    private void initFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().
                replace(fragmentContainer, fragment, tag)
                .commit();
    }

    @Override
    public void onSignInButtonPressed() {
        if (signInFragment ==null){
            signInFragment = new SignInFragment();
        }
        initFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSignUpButtonPressed() {
        if (signUpFragment ==null){
            signUpFragment = new SignUpFragment();
        }
        initFragment(signUpFragment, SIGN_UP_FRAGMENT_TAG);
        bottomNavigationView.setVisibility(View.GONE);

    }

    @Override
    public void openFragmentCalled(String fragmentTag) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        switch (fragmentTag) {
            case SIGN_IN_FRAGMENT_TAG:
                onSignInButtonPressed();
                break;
            case PROFILE_FRAGMENT_TAG:
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                }
                initFragment(profileFragment, fragmentTag);
                break;
            case ACCOUNTS_FRAGMENT_TAG:
                if (accountFragment == null) {
                    accountFragment = new AccountFragment();
                }
                initFragment(accountFragment, fragmentTag);
                break;
        }
    }
    
    @Override
    public void onTopButtonClick(String name) {
        for (String item : Globals.foodItems){
            if (item.equals(name)){
                Bundle args = new Bundle();
                args.putString(SEARCH_TERM, name);
                HomeFragment homeNew = new HomeFragment();
                homeNew.setArguments(args);
                initFragment(homeNew, HOME_FRAGMENT_TAG);
            }
        }
    }
    
    @Override
    public void onFoodItemClick(FoodItem food, ArrayList<FoodItem> foodItems) {
        Bundle args = new Bundle();
        args.putParcelable(Globals.FOOD_ITEM, food);
        args.putParcelableArrayList(Globals.FOOD_ITEMS, foodItems);
        FoodItemFragment foodItemFragment = new FoodItemFragment();
        foodItemFragment.setArguments(args);
        initFragment(foodItemFragment, Globals.FOOD_ITEM_FRAGMENT_TAG);
    }
    
    @Override
    public void onPlaceItemClick(PlaceModel place, ArrayList<PlaceModel> places) {
        Bundle args = new Bundle();
        args.putParcelable(Globals.PLACE_ITEM, place);
        args.putParcelableArrayList(Globals.PLACE_ITEMS, places);
        PlaceItemFragment placeItemFragment = new PlaceItemFragment();
        placeItemFragment.setArguments(args);
        initFragment(placeItemFragment, Globals.PLACE_ITEM_FRAGMENT_TAG);
    }
    
    
}