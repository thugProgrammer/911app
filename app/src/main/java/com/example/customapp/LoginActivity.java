package com.example.customapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.edit_login_email);
        password = findViewById(R.id.edit_password);

    }


    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    public void logUsers(View view) {
        if (isOk(email) && isOk(password)){

        }


    }

    public boolean isOk(EditText e){
        String value = e.getText().toString();
        return !value.isEmpty();
    }


    public void redirectToUserActivity(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);

    }


    private FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener(){


        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                redirectToUserActivity();
            } else {
                // No user is signed in
            }
        }
    };
}
