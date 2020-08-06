package com.alle.san.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    EditText nFullNames;
    EditText nEmail;
    EditText nPassword;
    EditText nRetypePassword;
    Button nCreateAccount;
    Button nHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nFullNames = findViewById(R.id.etPerName);
        nEmail = findViewById(R.id.etPerEmail);
        nPassword = findViewById(R.id.etPerPassword);
        nRetypePassword = findViewById(R.id.etPerRePassword);
        nCreateAccount = findViewById(R.id.btncreate_account);
        nHaveAccount = findViewById(R.id.btnhave_account);

        nHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        nCreateAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ArrayList<Person> users = new ArrayList<>();
                String pNames = nFullNames.getText().toString();
                String pEmail = nEmail.getText().toString();
                String pPassword = nPassword.getText().toString();
                String pRePassword = nRetypePassword.getText().toString();

                if(!pPassword.equals(pRePassword)){
                    /**
                    this code here crashes the app
                     **/
                    Toast toast = new Toast(getApplicationContext());
                    toast.setText("The Passwords you entered dont match");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else{
                    Person nPerson = new Person(pNames, pEmail, pPassword);
                    users.add(nPerson);
                    nFullNames.setText("");
                    nEmail.setText("");
                    nPassword.setText("");
                    nRetypePassword.setText("");

                }

            }
        });
    }
}