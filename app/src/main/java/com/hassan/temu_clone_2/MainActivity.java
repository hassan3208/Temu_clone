package com.hassan.temu_clone_2;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.AdRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView nav_bottom;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean notSignIn=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fabBot = findViewById(R.id.fab_bot);
        GoogleAccount googleAccount = new GoogleAccount(this);





        // check is the user sign in or not then adjust the main
        if (googleAccount.isUserSignedIn()) {

            notSignIn=false;


            LinearLayout signinRequest = findViewById(R.id.signin_request);
            ViewGroup parent = (ViewGroup) signinRequest.getParent();
            if (parent != null) {
                parent.removeView(signinRequest); // Completely removes the layout
            }

            // Adjust the margin of the FloatingActionButton
            ViewGroup.LayoutParams layoutParams = fabBot.getLayoutParams();

            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginParams.bottomMargin = 298; // Set bottom margin in pixels
                fabBot.setLayoutParams(marginParams);
            }
        }



        // checking internet status
        NetworkUtils.showInternetStatus(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new home())  // Load the Home fragment
                .commit();

        nav_bottom = findViewById(R.id.bottom_nav);

        nav_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                FragmentManager fm = getSupportFragmentManager();
                if (id == R.id.home) {
                    fm.beginTransaction().replace(R.id.frame, new home()).commit();
                } else if (id == R.id.search) {
                    fm.beginTransaction().replace(R.id.frame, new search()).commit();
                } else if (id == R.id.you) {
                    fm.beginTransaction().replace(R.id.frame, new You()).commit();
                } else {
                    fm.beginTransaction().replace(R.id.frame, new cart()).commit();
                }
                return true;
            }
        });

        // Initialize MobileAds SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Load and show the interstitial ad
        loadInterstitialAd();


        fabBot.setOnClickListener(v -> {
            BotDialogFragment botDialog = new BotDialogFragment();
            botDialog.show(getSupportFragmentManager(), "BotDialog");
        });

        if(notSignIn){
            Button signInButton = findViewById(R.id.sign_in_button);

            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, login_page.class);
                    startActivity(intent);
                }
            });
        }




    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(MainActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null; // Handle ad load failure
                    }
                });
    }
}
