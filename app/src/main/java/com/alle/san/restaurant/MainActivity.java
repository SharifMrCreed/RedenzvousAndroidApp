package com.alle.san.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomIconSelected);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_coontainer, new HomeFragment())
                .commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SearchView searchView = (SearchView)findViewById(R.id.action_search);
        if (item.getItemId() == R.id.action_search){
            Toast toast = new Toast(getApplicationContext());
            toast.setText("Your search was: " + searchView.getQuery());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        switch (bottomNavigationView.getSelectedItemId()){
            case(R.id.action_home):
                super.onBackPressed();
                break;
            case(R.id.action_chat):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_coontainer, new HomeFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.action_home);
                break;
            case(R.id.action_favourites):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_coontainer, new ChatsFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.action_chat);
                break;
            case(R.id.action_profile):
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_coontainer, new FavoritesFragment())
                        .commit();
                bottomNavigationView.setSelectedItemId(R.id.action_favourites);
                break;

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomIconSelected =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = new HomeFragment();
                    switch (item.getItemId()){
                        case(R.id.action_home):
                            selectedFragment = new HomeFragment();
                            break;
                        case(R.id.action_chat):
                            selectedFragment = new ChatsFragment();
                            break;
                        case(R.id.action_favourites):
                            selectedFragment = new FavoritesFragment();
                            break;
                        case(R.id.action_profile):
                            selectedFragment = new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_coontainer, selectedFragment)
                            .commit();
                    return true;
                }
            };



}