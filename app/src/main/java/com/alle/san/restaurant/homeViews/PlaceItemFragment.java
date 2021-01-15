package com.alle.san.restaurant.homeViews;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.MorePlaceRvAdapter;
import com.alle.san.restaurant.models.PlaceModel;
import com.alle.san.restaurant.utilities.Globals;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class PlaceItemFragment extends Fragment {
    ConstraintLayout parentLayout;
    RecyclerView similarItems;
    ImageView placePic;
    TextView locale, placeName;
    PlaceModel nPlaceModel = new PlaceModel();
    ArrayList<PlaceModel> morePlaces = new ArrayList<>();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            nPlaceModel = bundle.getParcelable(Globals.PLACE_ITEM);
            morePlaces = bundle.getParcelableArrayList(Globals.PLACE_ITEMS);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_item, container, false);
        placePic = view.findViewById(R.id.imageView);
        placeName = view.findViewById(R.id.tvPlaceName);
        locale = view.findViewById(R.id.tvLocation);
        similarItems = view.findViewById(R.id.rv_similar_items);
        parentLayout = view.findViewById(R.id.place_item_parent);
        initRecyclerViews();
        initViews();
    
        return view;
    }
    
    private void initViews() {
        Glide.with(getContext()).load(nPlaceModel.getLocalePics())
                .placeholder(R.drawable.image_icon)
                .fallback(R.drawable.broken_image_icon)
                .fitCenter()
                .into(placePic);
        locale.setText(nPlaceModel.getLocale());
        placeName.setText(nPlaceModel.getName());
//        Palette.from(nPlaceModel.getLocalePics()).generate(palette ->{
//            Palette.Swatch swatch = palette.getDominantSwatch;
//            if (swatch != null){
//                GradientDrawable parentBackground = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
//                        new int[]{ swatch.getRgb() , 0x00000000});
//                parentLayout.setBackground(parentBackground);
//                placeName.setTextColor(swatch.getTitleTextColor());
//                locale.setTextColor(swatch.getBodyTextColor());
//            }
//        });
    }
    
    private void initRecyclerViews() {
        similarItems.setAdapter(new MorePlaceRvAdapter(morePlaces, getContext()));
        similarItems.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }
}