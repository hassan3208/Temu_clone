package com.hassan.temu_clone_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Detail_item extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the views
        ImageView itemImage = findViewById(R.id.detailImage);
        TextView itemName = findViewById(R.id.detailName);
        TextView itemPrice = findViewById(R.id.detailPrice);

        // Get the data passed through the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageRes = extras.getInt("itemImage");
            String name = extras.getString("itemName");
            String price = extras.getString("itemPrice");
            Button addtocart = findViewById(R.id.addtocart);

            // Set the data to views
            itemImage.setImageResource(imageRes);
            itemName.setText(name);
            itemPrice.setText(price);

            addtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoogleAccount googleAccount = new GoogleAccount(Detail_item.this);

                    if(googleAccount.isUserSignedIn()){
                        cart cart = new cart();
                        cart.addToFirebase(imageRes, name, price);
                        Toast.makeText(Detail_item.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(Detail_item.this, login_page.class);
                        startActivity(intent);
                    }

                }
            });



            //nn
        }
    }
}
