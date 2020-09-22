package com.alle.san.restaurant.profileViews;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alle.san.restaurant.R;
import com.alle.san.restaurant.models.Person;
import com.alle.san.restaurant.utilities.Interactions;
import com.alle.san.restaurant.utilities.PersonContract;
import com.alle.san.restaurant.utilities.PersonDbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import static com.alle.san.restaurant.utilities.Globals.PROFILE_FRAGMENT_TAG;
import static com.alle.san.restaurant.utilities.Globals.SIGN_IN_FRAGMENT_TAG;


public class SignUpFragment extends Fragment {

    EditText nFullNames;
    EditText nEmail;
    EditText nPassword;
    EditText nRetypePassword;
    Button nCreateAccount;
    Button nHaveAccount;
    ProgressBar nProgressBar;
    Interactions interactions;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        nFullNames = view.findViewById(R.id.etPerName);
        nEmail = view.findViewById(R.id.etPerEmail);
        nPassword = view.findViewById(R.id.etPerPassword);
        nRetypePassword = view.findViewById(R.id.etPerRePassword);
        nCreateAccount = view.findViewById(R.id.btncreate_account);
        nHaveAccount = view.findViewById(R.id.btnhave_account);
        nProgressBar = view.findViewById(R.id.signUpProgressBar);

        passwordCriteria();
        setUpButtonClicks();

        return view;
    }

    private void passwordCriteria() {

        nPassword.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() < 8){
                nPassword.setError("Password too Short");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

        nRetypePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!(charSequence.toString().equals(nPassword.getText().toString()))){
                    nRetypePassword.setError("Passwords Don't Match");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpButtonClicks() {
        nHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactions.openFragmentCalled(SIGN_IN_FRAGMENT_TAG);
            }
        });
        nCreateAccount.setOnClickListener(new View.OnClickListener(){

            private FirebaseAuth authInstance;

            @Override
            public void onClick(View view) {
                final String pNames = nFullNames.getText().toString();
                String pEmail = nEmail.getText().toString();
                String pPassword = nPassword.getText().toString();
                String pRePassword = nRetypePassword.getText().toString();

                if (!pNames.isEmpty()) {
                    if (!pEmail.isEmpty()){
                        if (!pPassword.isEmpty()){
                            if (!pRePassword.isEmpty()){
                                if (pPassword.equals(pRePassword)){
                                    nProgressBar.setVisibility(View.VISIBLE);
                                    authInstance = FirebaseAuth.getInstance();
                                    authInstance.createUserWithEmailAndPassword(pEmail, pPassword)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(getActivity(), pNames + " is now Registered", Toast.LENGTH_LONG).show();
                                                        nProgressBar.setVisibility(View.GONE);
                                                        authInstance.signOut();
                                                        nFullNames.setText("");
                                                        nEmail.setText("");
                                                        nPassword.setText("");
                                                        nRetypePassword.setText("");
                                                        interactions.openFragmentCalled(PROFILE_FRAGMENT_TAG);
                                                    }else{
                                                        nProgressBar.setVisibility(View.GONE);
                                                        Toast.makeText(getActivity(), " Something Went wrong", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });


                                }else{
                                    nPassword.setError("Passwords didnt match");
                                    nRetypePassword.setError("Passwords didnt match");
                                    nPassword.setText("");
                                    nRetypePassword.setText("");
                                }
                            }else{
                                nRetypePassword.setText("");
                                nRetypePassword.setError("Required");
                            }
                        }else{
                                nPassword.setText("");
                                nPassword.setError("Required");
                                nRetypePassword.setText("");
                        }

                    }else{

                            nEmail.setText("");
                            nPassword.setError("Required");
                    }

                }else{
                    nFullNames.setText("");
                    nFullNames.setError("Required");
                }

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interactions = (Interactions) context;
    }
}