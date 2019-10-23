package com.example.customapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.customapp.async.AsyncWriter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends SignInActivity {
    private FirebaseAuth mAuth;
    private EditText editName;
    private EditText editPassword;
    private EditText editEmail;
    DatabaseReference databaseReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        editName = findViewById(R.id.edit_signup_name);
        editEmail = findViewById(R.id.edit_signup_email);
        editPassword = findViewById(R.id.edit_signup_password);
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onResume() {
        super.onResume();
        databaseReference = FirebaseDatabase.getInstance().getReference();


    }



    public void signUpUsers(View view) {

        if (isOk(editName) && isOk(editEmail) && isOk(editPassword)  ) {
            final String name = editName.getText().toString();
            final String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                String uid = user.getUid();
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("name", name);
                                hashMap.put("email", email);
                                hashMap.put("phone", "");
                                hashMap.put("address", "");
                                hashMap.put("age", "");
                                hashMap.put("lat", "");
                                hashMap.put("lgn", "");
                                hashMap.put("url", "");
                                AsyncWriter asyncWriter = new AsyncWriter(uid, hashMap, "WRITE");
                                asyncWriter.execute(databaseReference);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    }
}
