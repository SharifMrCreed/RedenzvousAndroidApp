package com.alle.san.restaurant.homeViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alle.san.restaurant.adapters.HomePagerAdapter;
import com.alle.san.restaurant.R;
import com.google.android.material.tabs.TabLayout;

import static com.alle.san.restaurant.utilities.Globals.SEARCH_TERM;

public class HomeFragment extends Fragment {

    FeedFragment feedFragment = new FeedFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            Bundle arg = new Bundle();
            arg.putString(SEARCH_TERM, bundle.getString(SEARCH_TERM));
            feedFragment.setArguments(arg);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View nView = inflater.inflate(R.layout.home_fragment, container, false);

        final ViewPager nViewPager = nView.findViewById(R.id.view_pager);
        TabLayout nTabLayout = nView.findViewById(R.id.home_frag_nest);

        setTabData(nTabLayout);
        nTabLayout.setupWithViewPager(nViewPager);
        final HomePagerAdapter adapter = new HomePagerAdapter(nTabLayout, getChildFragmentManager(), nTabLayout.getTabCount(), feedFragment);
        nViewPager.setAdapter(adapter);

        nViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(nTabLayout));

        nTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                nViewPager.setCurrentItem(tab.getPosition());
                //
                //TODO: Save me in a view Model or a bundle
                //
                //
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
