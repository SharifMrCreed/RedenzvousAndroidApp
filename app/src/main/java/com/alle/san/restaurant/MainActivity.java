package com.alle.san.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.alle.san.restaurant.homeViews.FoodItemFragment;
import com.alle.san.restaurant.homeViews.HomeFragment;
import com.alle.san.restaurant.homeViews.PlaceItemFragment;
import com.alle.san.restaurant.models.food.FoodItem;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.profileViews.ProfileFragment;
import com.alle.san.restaurant.profileViews.SignInFragment;
import com.alle.san.restaurant.profileViews.SignUpFragment;
import com.alle.san.restaurant.utilities.Globals;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static com.alle.san.restaurant.utilities.Globals.ACCOUNTS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.CHATS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.FAVOURITES_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.FOOD_ITEM_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.HOME_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SEARCH_TERM;
import static com.alle.san.restaurant.utilities.Globals.SIGN_IN_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_UP_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements ViewChanger {

    Toolbar toolBar;
    DrawerLayout drawer;
    NavigationView nNavigationView;
    
    
    private int fragmentContainer;
    HomeFragment homeFragment;
    ChatsFragment chatsFragment;
    FavoritesFragment favoritesFragment;
    SearchView searchView;
    SignUpFragment signUpFragment;
    SignInFragment signInFragment;
    ProfileFragment profileFragment;
    private ActionBar supportActionBar;
    private FirebaseUser user;
    ArrayList<String> allTags = new ArrayList<>();
    
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            int i = allTags.size() - 1;
            if (HOME_FRAGMENT_TAG.equals(allTags.get(i))){
                super.onBackPressed();
            }else if (PROFILE_FRAGMENT_TAG.equals(allTags.get(i)) || CHATS_FRAGMENT_TAG.equals(allTags.get(i))
            || FAVOURITES_FRAGMENT_TAG.equals(allTags.get(i))){
                initFragment(homeFragment, HOME_FRAGMENT_TAG);
            }else if (FOOD_ITEM_FRAGMENT_TAG.equals(allTags.get(i))){
                initFragment(homeFragment, HOME_FRAGMENT_TAG);
            }
            
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.appToolBar);
        drawer = findViewById(R.id.drawer_layout);
        nNavigationView = findViewById(R.id.nav_view);
        fragmentContainer = R.id.fragment_container;

        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initNavigationView();
        supportActionBar = getSupportActionBar();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(savedInstanceState == null) {
            if (homeFragment == null){
                homeFragment = new HomeFragment();
            }
            initFragment(homeFragment, HOME_FRAGMENT_TAG);
        }

    }
    
    private void initNavigationView() {
        nNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case (R.id.nav_home):
                    initFragment(new HomeFragment(), HOME_FRAGMENT_TAG);
                    break;
                case (R.id.nav_favs):
                    initFragment(new FavoritesFragment(), FAVOURITES_FRAGMENT_TAG);
                    break;
                    case (R.id.nav_messages):
                    initFragment(new ChatsFragment(), CHATS_FRAGMENT_TAG);
                    break;
                    case (R.id.nav_profile):
                        if (user != null){
                            initFragment(new ProfileFragment(), PROFILE_FRAGMENT_TAG);
                        }else {
                            initFragment(new SignInFragment(), SIGN_IN_FRAGMENT_TAG);
                        }
                    break;
                    case (R.id.nav_my_foods):
                        //TODO: TBI.
                    break;
                    case (R.id.nav_my_places):
                        //TODO: TBI..
                    break;
                    case (R.id.nav_settings):
                        //TODO: TBI...
                    break;
                    case (R.id.nav_theme):
                        //TODO: TBI....
                    break;
                    case (R.id.nav_logout):
                        if (user != null){
                            FirebaseAuth.getInstance().signOut();
                        }
                    break;
                default:
                    drawer.closeDrawer(GravityCompat.START);
                    return  false;
            }
            drawer.closeDrawer(GravityCompat.START);
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
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
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
        return super.onCreateOptionsMenu(menu);
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
        switch (item.getItemId()) {
            case (R.id.action_chat):
                if (chatsFragment == null) {
                    chatsFragment = new ChatsFragment();
                }
                initFragment(chatsFragment, CHATS_FRAGMENT_TAG);
                return true;
    
            case (R.id.action_profile):
                if (user != null) {
                    if (profileFragment == null) {
                        profileFragment = new ProfileFragment();
                    }
                } else {
                    if (signInFragment == null) {
                        signInFragment = new SignInFragment();
                    }
                    initFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   

    private void initFragment(Fragment fragment, String tag) {
        if (allTags.size() != 0) {
            if ((tag.equals(allTags.get((allTags.size() - 1))))) {
                allTags.remove(allTags.size() - 1);
            }
            if (allTags.size() > 1){
                if ((tag.equals(allTags.get((allTags.size() - 2))))) {
                    allTags.remove(allTags.size() - 2);
                }
            }
            
        }
        allTags.add(tag);
        getSupportFragmentManager().beginTransaction().
                addToBackStack(tag).
                replace(fragmentContainer, fragment, tag)
                .commit();
    }
    

    @Override
    public void onSignInButtonPressed() {
        if (signInFragment ==null){
            signInFragment = new SignInFragment();
        }
        initFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
    }

    @Override
    public void onSignUpButtonPressed() {
        if (signUpFragment ==null){
            signUpFragment = new SignUpFragment();
        }
        initFragment(signUpFragment, SIGN_UP_FRAGMENT_TAG);

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
                if (signInFragment == null){
                    signInFragment = new SignInFragment();
                }
                initFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
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
    public void onFoodItemClick(FoodItem food, ArrayList<FoodItem> foodItems, ImageView pic, TextView foodName, TextView placeName) {
        Bundle args = new Bundle();
        args.putParcelable(Globals.FOOD_ITEM, food);
        args.putParcelableArrayList(Globals.FOOD_ITEMS, foodItems);
        FoodItemFragment foodItemFragment = new FoodItemFragment();
        foodItemFragment.setArguments(args);
        if (!(Globals.FOOD_ITEM_FRAGMENT_TAG.equals(allTags.get((allTags.size() - 1))))) {
            allTags.add(Globals.FOOD_ITEM_FRAGMENT_TAG);
            getSupportFragmentManager().beginTransaction().
                    replace(fragmentContainer, foodItemFragment, Globals.FOOD_ITEM_FRAGMENT_TAG)
                    .addSharedElement(pic, "feedImageTN")
                    .addSharedElement(foodName, "feedFoodNameTN")
                    .addSharedElement(placeName, "feedPlaceNameTN")
                    .addToBackStack(Globals.FOOD_ITEM_FRAGMENT_TAG)
                    .commit();
        } else{
            getSupportFragmentManager().beginTransaction().
                    replace(fragmentContainer, foodItemFragment, Globals.FOOD_ITEM_FRAGMENT_TAG)
                    .addSharedElement(pic, "feedImageTN")
                    .addSharedElement(foodName, "feedFoodNameTN")
                    .addSharedElement(placeName, "feedPlaceNameTN")
                    .commit();
        }
    }
    
    @Override
    public void onPlaceItemClick(PlaceItem place, ArrayList<PlaceItem> places, ImageView pic, TextView placeName) {
        Bundle args = new Bundle();
        args.putParcelable(Globals.PLACE_ITEM, place);
        args.putParcelableArrayList(Globals.PLACE_ITEMS, places);
        PlaceItemFragment placeItemFragment = new PlaceItemFragment();
        placeItemFragment.setArguments(args);
        if (!(Globals.PLACE_ITEM_FRAGMENT_TAG.equals(allTags.get((allTags.size() - 1))))) {
            allTags.add(Globals.PLACE_ITEM_FRAGMENT_TAG);
            getSupportFragmentManager().beginTransaction().
                    replace(fragmentContainer, placeItemFragment, Globals.PLACE_ITEM_FRAGMENT_TAG)
                    .addSharedElement(pic, "placePlaceImageTN")
                    .addSharedElement(placeName, "placePlaceNameTN")
                    .addToBackStack(Globals.PLACE_ITEM_FRAGMENT_TAG)
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction().
                    replace(fragmentContainer, placeItemFragment, Globals.PLACE_ITEM_FRAGMENT_TAG)
                    .addSharedElement(pic, "placePlaceImageTN")
                    .addSharedElement(placeName, "placePlaceNameTN")
                    .commit();
        }
    }
    
    
}