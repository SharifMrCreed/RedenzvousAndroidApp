package com.alle.san.restaurant.profileViews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alle.san.restaurant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    EditText userEmail, userName;


    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userEmail = view.findViewById(R.id.etPerEmail);
        userName = view.findViewById(R.id.fullName);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            if (user.getUid() != null){
                userName.setText(user.getUid());
            }
            userEmail.setText(user.getEmail());
        }
        return view;
    }
}