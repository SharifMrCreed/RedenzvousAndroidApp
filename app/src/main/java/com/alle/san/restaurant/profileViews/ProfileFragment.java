package com.alle.san.restaurant.profileViews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.alle.san.restaurant.utilities.Globals.ACCOUNTS_FRAGMENT_TAG;


public class ProfileFragment extends Fragment {
    EditText userEmail, userName, phoneNumber, displayName, bEdit,
            displayNames, userNames;
    Button bLogOut;

    ViewChanger viewChanger;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userEmail = view.findViewById(R.id.etPerEmail);
        userName = view.findViewById(R.id.user_name);
        phoneNumber = view.findViewById(R.id.phone_number);
        displayName = view.findViewById(R.id.displayNames);
        bEdit = view.findViewById(R.id.edit_info);
        displayNames = view.findViewById(R.id.display_name);
        userNames = view.findViewById(R.id.userName);
        bLogOut = view.findViewById(R.id.btn_logout);

        user = FirebaseAuth.getInstance().getCurrentUser();

        initClicks();
        initViewInfo();

        return view;
    }

    private void initViewInfo() {
        if (user != null){
            userName.setText(user.getDisplayName());
            userNames.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());
        }
    }

    private void initClicks() {
        bLogOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            viewChanger.openFragmentCalled(ACCOUNTS_FRAGMENT_TAG);
        });

        bEdit.setOnClickListener(view -> {
            /* TODO: TO BE IMPLEMENTED*/
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewChanger = (ViewChanger) context;
    }
}