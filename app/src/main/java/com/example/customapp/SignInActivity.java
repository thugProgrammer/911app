package com.example.customapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText editEmail;
    private EditText editPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.edit_signin_email);
        editPassword = findViewById(R.id.edit_signin_password);

    }


    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void singInUsers(View view) {
        if (isOk(editEmail) && isOk(editPassword)){
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Log.d("LOGIN", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        redirectToUserActivity("SUCCESSFUL");
                    }else{
                        Toast.makeText(SignInActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }


//    public void onResume() {
//        super.onResume();
//    }

    public boolean isOk(EditText e){
        String value = e.getText().toString();
        return !value.isEmpty();
    }


    public void redirectToUserActivity(String flag){
        if (flag.equals("SUCCESSFUL")){
            Intent signInIntent = new Intent(this, UserActivity.class);
            startActivity(signInIntent);
        }else {

        }

    }


    protected FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener(){


        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                redirectToUserActivity("SUCCESSFUL");
            } else {
                // No user is signed in
            }
        }
    };

    public void redirectToSignUp(View view) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
}
