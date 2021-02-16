package com.alle.san.restaurant.homeViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.AdRvAdapter;
import com.alle.san.restaurant.utilities.AdUtil;


public class PartyFragment extends Fragment {

    RecyclerView rvPlaces;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.party_fragment, container,false);
        rvPlaces = view.findViewById(R.id.partyRv);
        initAdsRv();

        return view;

    }

    private void initAdsRv() {

        rvPlaces.setAdapter(new AdRvAdapter(AdUtil.getPlaces(), getContext()));
        rvPlaces.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}
