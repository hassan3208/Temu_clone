package com.hassan.temu_clone_2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class countries extends AppCompatActivity {

    private ArrayList<String> countryList; // To hold all countries
    private LinearLayout linearLayout;
    private TextView selectedTextView = null; // To track the currently selected TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        EditText searchBar = findViewById(R.id.searchBar);
        linearLayout = findViewById(R.id.linearLayout);

        // Load all country names
        countryList = new ArrayList<>();
        String[] countries = Locale.getISOCountries();
        for (String countryCode : countries) {
            Locale locale = new Locale("", countryCode);
            countryList.add(locale.getDisplayCountry());
        }

        // Display all countries initially
        displayCountries(countryList);

        // Add a TextWatcher to the search bar
        searchBar.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter countries based on search query
                filterCountries(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                // No action needed
            }
        });
    }


    private String getCurrencyCode(String countryName) {
        String[] countryCodes = Locale.getISOCountries();

        for (String code : countryCodes) {
            Locale locale = new Locale("", code);
            if (locale.getDisplayCountry().equalsIgnoreCase(countryName)) {
                // Get the currency for the country
                Currency currency = Currency.getInstance(locale);
                return currency.getCurrencyCode();  // Return the currency code (e.g., "USD", "INR", "PKR")
            }
        }

        return null;  // Return null if the country name is not found or the currency can't be determined
    }



    private String getCountryCode(String countryName) {
        String[] countryCodes = Locale.getISOCountries();

        for (String code : countryCodes) {
            Locale locale = new Locale("", code);
            if (locale.getDisplayCountry().equalsIgnoreCase(countryName)) {
                return code;  // Return the country code (e.g., "PK" for Pakistan)
            }
        }

        return null;  // Return null if the country name is not found
    }
    private void displayCountries(ArrayList<String> countries) {
        linearLayout.removeAllViews(); // Clear the previous list

        for (String country : countries) {
            // Create a new TextView for each country
            TextView textView = new TextView(this);
            textView.setText("  " + country);
            textView.setTextSize(18);
            textView.setPadding(10, 20, 10, 20);
            textView.setBackgroundColor(Color.WHITE);

            // Add click listener to TextView
            textView.setOnClickListener(v -> {
                // Deselect the previously selected TextView
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundColor(Color.WHITE);
                    selectedTextView.setText(selectedTextView.getText().toString().replace(" ✔️", ""));
                }

                // Select the clicked TextView
                textView.setBackgroundColor(Color.LTGRAY);
                textView.setText("  " + country + " ✔️");
                selectedTextView = textView;


                String countryCode=getCountryCode(country);
                String currencyCode=getCurrencyCode(country);

                SharedPreferences countryDetails=getSharedPreferences("COUNTRY_DETAILS",MODE_PRIVATE);
                SharedPreferences.Editor editor=countryDetails.edit();
                editor.putString("COUNTRY_CODE",countryCode);
                editor.putString("CURRENCY_CODE",currencyCode);
                editor.apply();



                TextView back = findViewById(R.id.back);

// Set a touch listener to handle clicks specifically on the drawableLeft
                back.setOnTouchListener((v1, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        // Check if the touch is within the bounds of the drawableLeft
                        if (event.getX() <= back.getCompoundDrawables()[0].getBounds().width() + back.getPaddingStart()) {
                            // Move to the next activity when the drawableLeft is clicked
                            Intent intent = new Intent(countries.this, settings_next.class);
                            startActivity(intent);
                            return true; // Consume the event
                        }
                    }
                    return false; // Pass the event to the default handler
                });





            });

            // Add the TextView to the LinearLayout
            linearLayout.addView(textView);
        }
    }

    private void filterCountries(String query) {
        ArrayList<String> filteredList = new ArrayList<>();

        for (String country : countryList) {
            if (country.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(country);
            }
        }

        // Display the filtered list
        displayCountries(filteredList);
    }
}
