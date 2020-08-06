package com.alle.san.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View nView = inflater.inflate(R.layout.home_fragment, container, false);
        final ViewPager nViewPager = nView.findViewById(R.id.view_pager);
        TabLayout nTabLayout = nView.findViewById(R.id.home_frag_nest);
        setTabData(nTabLayout);
        nTabLayout.setupWithViewPager(nViewPager);
        final HomePagerAdapter adapter = new HomePagerAdapter(nTabLayout, getChildFragmentManager(), nTabLayout.getTabCount());
        nViewPager.setAdapter(adapter);

        nViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(nTabLayout));

        nTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                nViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return nView;
    }



    private void setTabData(TabLayout tabLayout){
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
