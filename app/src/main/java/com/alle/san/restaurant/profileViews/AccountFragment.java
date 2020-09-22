package com.alle.san.restaurant.profileViews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.utilities.Interactions;


public class AccountFragment extends Fragment {
    Button bSignUp;
    Button bSignIn;
    Interactions viewInteractions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        bSignIn = view.findViewById(R.id.bSignIn);
        bSignUp = view.findViewById(R.id.bSignUp);


        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewInteractions.onSignInButtonPressed();

            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewInteractions.onSignUpButtonPressed();

            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewInteractions = (Interactions) context;
    }

}