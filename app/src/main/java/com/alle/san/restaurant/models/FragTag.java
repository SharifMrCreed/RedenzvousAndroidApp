package com.alle.san.restaurant.models;

import androidx.fragment.app.Fragment;

public class FragTag {
    private String tag;
    private Fragment nFragment;
    
    public FragTag(String tag, Fragment fragment) {
        this.tag = tag;
        nFragment = fragment;
    }
    
    public String getTag() {
        return tag;
    }
    
    public Fragment getFragment() {
        return nFragment;
    }
}
