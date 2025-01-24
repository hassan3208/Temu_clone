package com.hassan.temu_clone_2;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hassan.temu_clone_2.databinding.ActivityLoginPageBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class login_page extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100; // Request code for Google Sign-In
    private GoogleAccount googleAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize GoogleAccount
        googleAccount = new GoogleAccount(this);


        //check is the user sign in or not
        if(googleAccount.isUserSignedIn()){
            Intent intent = new Intent(login_page.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        TextView close = findViewById(R.id.close);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button google_btn = findViewById(R.id.google_btn);

        // Set a click listener for close the sign-in page
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!googleAccount.isUserSignedIn()){
                    Intent intent = new Intent(login_page.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                finish(); // Optional: Close the current activity
            }
        });

        // Set a click listener for the Google Sign-In button
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleAccount.signUpWithGoogle(login_page.this, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            googleAccount.loginWithGoogle(data, task -> {
                if (task.isSuccessful()) {
                    // Get Firebase user
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user != null) {
                        // Create a reference to the database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("users");

                        // Create a user object
                        GoogleUser googleUser = new GoogleUser(
                                user.getDisplayName(),
                                user.getEmail(),
                                user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null,
                                new ArrayList<>()
                        );

                        // Save user data in the database under their UID
                        usersRef.child(user.getUid()).setValue(googleUser)
                                .addOnCompleteListener(saveTask -> {
                                    if (saveTask.isSuccessful()) {
                                        Log.d("login_page", "User data saved successfully");
                                        // Navigate to the next activity
                                        Intent intent = new Intent(login_page.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.e("login_page", "Failed to save user data", saveTask.getException());
                                    }
                                });
                    }
                } else {
                    // Handle sign-in failure
                    Log.e("login_page", "Google Sign-In failed", task.getException());
                }
            });
        }
    }

}
