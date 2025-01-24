package com.hassan.temu_clone_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the splash screen layout
        setContentView(R.layout.activity_splash);

        // Delay for 3 seconds before transitioning to the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Home Activity after 3 seconds
                Intent intent = new Intent(splash.this, login_page.class);
                startActivity(intent);
                finish(); // Finish the SplashActivity so the user can't go back to it
            }
        }, 2000); // 3000 milliseconds = 3 seconds
    }
}
