package com.alle.san.restaurant.profileViews;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.utilities.ViewChanger;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;

public class SignInFragment extends Fragment {
    TextView userEmail, userPassword;
    Button signInButton, registerButton, forgotPasswordButton;
    ProgressBar progressBar;
    FirebaseAuth.AuthStateListener authStateListener;
    ViewChanger viewChanger;

    private static final String TAG = "SignInFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        userEmail = view.findViewById(R.id.user_email);
        userPassword = view.findViewById(R.id.password);
        signInButton = view.findViewById(R.id.bSignIn);
        registerButton = view.findViewById(R.id.register);
        forgotPasswordButton = view.findViewById(R.id.forgot_password);
        progressBar = view.findViewById(R.id.signInProgressBar);

        initButtons();
        initFirebaseAuthentication();

        return view;
    }

    private void initFirebaseAuthentication() {
        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null){
                Log.d(TAG, "initFirebaseAuthentication: User Not Null");
            }else{

                Log.d(TAG, "initFirebaseAuthentication: User is Null");
            }
        };
    }

    private void initButtons() {
        signInButton.setOnClickListener(view ->{
            progressBar.setVisibility(View.VISIBLE);
            checkFields();
        });

        registerButton.setOnClickListener(view -> viewChanger.onSignUpButtonPressed());
        forgotPasswordButton.setOnClickListener(view -> {}/* TODO: Implement Forgot Password*/);
    }

    private void checkFields() {
        if ((!userEmail.getText().toString().equals(""))){
            if ((userEmail.getText().toString().contains("@"))){
                if (!userPassword.getText().toString().equals("")){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    viewChanger.openFragmentCalled(PROFILE_FRAGMENT_TAG);
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            }).addOnFailureListener(e -> {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Email or Password may be wrong", Toast.LENGTH_LONG).show();


                    });

                }else {
                    userPassword.setText("");
                    userPassword.setError("Required");
                }
            }else{
                userEmail.setError("Not a Valid Email");
            }
        }else{
            userEmail.setText("");
            userEmail.setError("Required");

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewChanger = (ViewChanger) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }
}