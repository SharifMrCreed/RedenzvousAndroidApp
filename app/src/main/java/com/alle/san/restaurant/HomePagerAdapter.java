package com.alle.san.restaurant;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    int totalTabs;
    TabLayout tabLayout;

    public HomePagerAdapter(TabLayout tabLayout, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs=totalTabs;
        this.tabLayout = tabLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.foodies);
                return new FeedsFragment();
            case 1:
                Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.places);
                return new PicksFragment();
            case 2:
                Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(R.drawable.partyy);
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
