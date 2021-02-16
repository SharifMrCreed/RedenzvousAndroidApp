package com.alle.san.restaurant.homeViews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.adapters.MorePlaceRvAdapter;
import com.alle.san.restaurant.models.place.PlaceItem;
import com.alle.san.restaurant.utilities.Globals;
import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class PlaceItemFragment extends Fragment {
    ConstraintLayout parentLayout;
    RecyclerView similarItems;
    ImageView placePic;
    TextView locale, placeName;
    PlaceItem nPlaceItem = new PlaceItem();
    ArrayList<PlaceItem> morePlaces = new ArrayList<>();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            nPlaceItem = bundle.getParcelable(Globals.PLACE_ITEM);
            morePlaces = bundle.getParcelableArrayList(Globals.PLACE_ITEMS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.enter_transition));
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.enter_transition));
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_item, container, false);
        placePic = view.findViewById(R.id.user_image);
        placeName = view.findViewById(R.id.tvPlaceName);
        locale = view.findViewById(R.id.tvLocation);
        similarItems = view.findViewById(R.id.rv_similar_items);
        parentLayout = view.findViewById(R.id.place_item_parent);
        initRecyclerViews();
        try {
            initViews();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    
        return view;
    }
    
    private void initViews() throws MalformedURLException {
        Glide.with(getContext()).load(nPlaceItem.getPhotos()[0])
                .placeholder(R.drawable.image_icon)
                .fallback(R.drawable.broken_image_icon)
                .fitCenter()
                .into(placePic);
        locale.setText(nPlaceItem.getVicinity());
        placeName.setText(nPlaceItem.getName());
        Bitmap bitmap = Globals.getBitmapAt(new URL(Globals.getLink(nPlaceItem.getPhotos()[0])));
        if (bitmap == null){
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
        }
        Palette.from(bitmap).generate(palette ->{
            Palette.Swatch swatch = palette.getVibrantSwatch();
            if (swatch != null){
                GradientDrawable parentBackground = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                        new int[]{ 0x00000000, swatch.getRgb() });
                parentLayout.setBackground(parentBackground);
                placeName.setTextColor(swatch.getTitleTextColor());
                locale.setTextColor(swatch.getBodyTextColor());
            }
        });
    }
    
    private void initRecyclerViews() {
        similarItems.setAdapter(new MorePlaceRvAdapter(morePlaces, getContext()));
        similarItems.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }
}