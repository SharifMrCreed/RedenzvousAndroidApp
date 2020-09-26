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
import com.alle.san.restaurant.adapters.PlacesRvAdapter;
import com.alle.san.restaurant.models.PlaceModel;
import com.alle.san.restaurant.utilities.PlaceUtil;

import java.util.ArrayList;

public class PicksFragment extends Fragment {

    RecyclerView rvPlaces;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picks_fragment, container, false);
        rvPlaces = view.findViewById(R.id.placesRv);
        initPlacesRv();

        return view;
    }

    private void initPlacesRv() {
        rvPlaces.setAdapter(new PlacesRvAdapter(PlaceUtil.getPlaces(), getContext()));
        rvPlaces.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}
