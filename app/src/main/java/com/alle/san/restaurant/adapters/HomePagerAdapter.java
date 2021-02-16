package com.alle.san.restaurant.adapters;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alle.san.restaurant.homeViews.FoodFragment;
import com.alle.san.restaurant.homeViews.PartyFragment;
import com.alle.san.restaurant.homeViews.PlaceFragment;
import com.google.android.material.tabs.TabLayout;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    int totalTabs;
    TabLayout tabLayout;
    FoodFragment nFoodFragment;

    public HomePagerAdapter(TabLayout tabLayout, @NonNull FragmentManager fm, int totalTabs, FoodFragment foodFragment) {
        super(fm);
        this.totalTabs=totalTabs;
        this.tabLayout = tabLayout;
        this.nFoodFragment = foodFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return nFoodFragment;
            case 1:
                return new PlaceFragment();
            case 2:
                return new PartyFragment();
            default:
                return null;

        }
    }


    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Foodies";
            case 1:
                return "Places";
            case 2:
                return "Party";
            default:
                return null;

        }
    }
}
