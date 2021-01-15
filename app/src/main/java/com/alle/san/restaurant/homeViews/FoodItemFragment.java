package com.alle.san.restaurant.homeViews;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.alle.san.restaurant.adapters.SimilarRvAdapter;
import com.alle.san.restaurant.models.FoodItem;
import com.alle.san.restaurant.utilities.Globals;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodItemFragment extends Fragment {
    
    ConstraintLayout parentLayout;
    RecyclerView similarItems, sameMenu;
    ImageView foodPic;
    TextView foodName, placeName;
    FoodItem nFoodItem = new FoodItem();
    ArrayList<FoodItem> nFoodItems = new ArrayList<>();
    ArrayList<FoodItem> moreItems = new ArrayList<>();
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            nFoodItem = bundle.getParcelable(Globals.FOOD_ITEM);
            nFoodItems = bundle.getParcelableArrayList(Globals.FOOD_ITEMS);
        }
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_item, container, false);
        foodPic = view.findViewById(R.id.imageView);
        foodName = view.findViewById(R.id.tvFoodName);
        placeName = view.findViewById(R.id.tvPlaceName);
        sameMenu = view.findViewById(R.id.rv_same_place);
        similarItems = view.findViewById(R.id.rv_similar_items);
        parentLayout = view.findViewById(R.id.food_item_parent);
        initRecyclerViews();
        initViews();
        return view;
    }
    
    private void initViews() {
        Glide.with(getContext()).load(nFoodItem.getImage())
                .placeholder(R.drawable.image_icon)
                .fallback(R.drawable.broken_image_icon)
                .fitCenter()
                .into(foodPic);
        placeName.setText(nFoodItem.getRestaurantName());
        foodName.setText(nFoodItem.getName());
//        Palette.from(nFoodItem.getImage()).generate(palette ->{
//            Palette.Swatch swatch = palette.getDominantSwatch;
//            if (swatch != null){
//                GradientDrawable parentBackground = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
//                        new int[]{ swatch.getRgb() , 0x00000000});
//                parentLayout.setBackground(parentBackground);
//                foodName.setTextColor(swatch.getTitleTextColor());
//                placeName.setTextColor(swatch.getBodyTextColor());
//            }
//        });
    }
    
    private void initRecyclerViews() {
        moreItems = getMoreItems();
        sameMenu.setAdapter(new SimilarRvAdapter(moreItems, getContext()));
        sameMenu.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        similarItems.setAdapter(new SimilarRvAdapter(nFoodItems, getContext()));
        similarItems.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }
    
    private ArrayList<FoodItem> getMoreItems() {
        ArrayList<FoodItem> items = new ArrayList<>();
        for (FoodItem food : nFoodItems){
            if (food.getRestaurantName().equals(nFoodItem.getRestaurantName())){
                items.add(food);
            }
        }
        return items;
    }
}