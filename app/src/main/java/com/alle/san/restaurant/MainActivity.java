package com.alle.san.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.alle.san.restaurant.homeViews.HomeFragment;
import com.alle.san.restaurant.profileViews.AccountFragment;
import com.alle.san.restaurant.profileViews.ProfileFragment;
import com.alle.san.restaurant.profileViews.SignInFragment;
import com.alle.san.restaurant.profileViews.SignUpFragment;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.alle.san.restaurant.utilities.Globals.ACCOUNTS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.CHATS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.FAVOURITES_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.HOME_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_IN_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_UP_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements ViewChanger {

    BottomNavigationView bottomNavigationView;
    private SearchView searchView;
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
        searchView = findViewById(R.id.action_search);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Toast.makeText(getApplicationContext(), " Searching.....", Toast.LENGTH_LONG)
                            .show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

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


}