package com.alle.san.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.alle.san.restaurant.homeViews.HomeFragment;
import com.alle.san.restaurant.profileViews.AccountFragment;
import com.alle.san.restaurant.profileViews.ProfileFragment;
import com.alle.san.restaurant.profileViews.SignInFragment;
import com.alle.san.restaurant.profileViews.SignUpFragment;
import com.alle.san.restaurant.utilities.Interactions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.alle.san.restaurant.utilities.Globals.ACCOUNTS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.CHATS_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.FAVOURITES_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.HOME_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_IN_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_UP_FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements Interactions {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        searchView = findViewById(R.id.action_search);
        toolBar = findViewById(R.id.appToolBar);
        fragmentContainer = R.id.fragment_coontainer;

        setSupportActionBar(toolBar);
        supportActionBar = getSupportActionBar();

        initBottomNavigationView();
        initFragment(new HomeFragment(), HOME_FRAGMENT_TAG);


    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = new HomeFragment();
                String tag = null;
                switch (item.getItemId()){
                    case(R.id.action_home):
                        tag = HOME_FRAGMENT_TAG;
                        if (supportActionBar != null && (!supportActionBar.isShowing())){
                            supportActionBar.show();
                        }
                        break;
                    case(R.id.action_chat):
                        selectedFragment = chatsFragment = new ChatsFragment();
                        tag = CHATS_FRAGMENT_TAG;
                        if (supportActionBar != null && (!supportActionBar.isShowing())){
                            supportActionBar.show();
                        }
                        break;
                    case(R.id.action_favourites):
                        selectedFragment = favoritesFragment = new FavoritesFragment();
                        tag = FAVOURITES_FRAGMENT_TAG;
                        if (supportActionBar != null && (!supportActionBar.isShowing())){
                        supportActionBar.show();
                    }
                        break;
                    case(R.id.action_profile):
                        selectedFragment = accountFragment = new AccountFragment();
                        tag = ACCOUNTS_FRAGMENT_TAG;
                        if (supportActionBar != null && (supportActionBar.isShowing())){
                            supportActionBar.hide();
                        }
                        break;

                }
                initFragment(selectedFragment, tag);
                return true;
            }
        });
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
                    initFragment(new HomeFragment(), HOME_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_home);
                    break;
                case(R.id.action_favourites):
                    initFragment(chatsFragment == null? new ChatsFragment() : chatsFragment, CHATS_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_chat);
                    break;
                case(R.id.action_profile):
                    initFragment(favoritesFragment == null? new FavoritesFragment() : favoritesFragment, FAVOURITES_FRAGMENT_TAG);
                    bottomNavigationView.setSelectedItemId(R.id.action_favourites);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    if (supportActionBar != null && (!supportActionBar.isShowing())){
                        supportActionBar.show();
                    }
                    break;

            }
        }else{
            initFragment(accountFragment == null? new AccountFragment() : accountFragment, ACCOUNTS_FRAGMENT_TAG);
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
        if (fragmentTag.equals(SIGN_IN_FRAGMENT_TAG)){
            onSignInButtonPressed();
        }else if (fragmentTag.equals(PROFILE_FRAGMENT_TAG)){
            if (profileFragment == null){
                profileFragment = new ProfileFragment();
            }
            initFragment(profileFragment, fragmentTag);
        }
    }
}