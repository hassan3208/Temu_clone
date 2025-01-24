package com.hassan.temu_clone_2;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class settings_next extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_next);


        GoogleAccount googleAccount = new GoogleAccount(this);

        if (googleAccount.isUserSignedIn()){
            TextView sign_in = findViewById(R.id.sign_in);
            sign_in.setText("Click me to delete account");
        }

        // Find the Button in the activity's layout
        TextView country = findViewById(R.id.country);

        String buttonText=country.getText().toString();

        SharedPreferences countryDetails=getSharedPreferences("COUNTRY_DETAILS",MODE_PRIVATE);
        String countryCode=countryDetails.getString("COUNTRY_CODE","PK");
        String currencyCode=countryDetails.getString("CURRENCY_CODE","PKR");


        //String countryCode = getIntent().getStringExtra("COUNTRY_CODE");
        if (countryCode != null) {
            // Set the text of the Button to the country code
            country.append("   "+countryCode);
        }else {
            country.append("    PK");
        }


        TextView currency = findViewById(R.id.currency);

        //String currencyCode = getIntent().getStringExtra("CURRENCY_CODE");  // Get the currency code passed via the intent

        String baseText = "   Currency ";
        String currencyDisplay = (currencyCode != null) ? currencyCode : "PKR";
        String fullText = baseText + " " + currencyDisplay;

        SpannableString spannableString = new SpannableString(fullText);

        currency.setText(spannableString);



        // Set the OnClickListener
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start another activity
                Intent intent = new Intent(settings_next.this, countries.class);
                startActivity(intent);
            }
        });
    }

    public void go_to_login(View view){

        GoogleAccount googleAccount = new GoogleAccount(this);

        if (!googleAccount.isUserSignedIn()){
            Intent intent = new Intent(settings_next.this, login_page.class);
            startActivity(intent);
        }
        else{

            new AlertDialog.Builder(this)
                    .setTitle("Sign Out")
                    .setMessage("Do you really want to delete account?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        googleAccount.signOut(this);
                        TextView sign_in = findViewById(R.id.sign_in);
                        sign_in.setText("Click me to Sign In");

                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Dismiss dialog, do nothing
                        dialog.dismiss();
                    })
                    .show();




        }
    }
}
