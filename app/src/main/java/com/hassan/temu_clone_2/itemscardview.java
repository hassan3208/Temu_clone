package com.hassan.temu_clone_2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class itemscardview {
    public static CardView createCardView(Context context, items item) {
        CardView cardView = new CardView(context);

        // Set layout parameters for the CardView
        GridLayout.LayoutParams cardParams = new GridLayout.LayoutParams();
        cardParams.width = 0; // Use 0 to allow weight-based sizing
        cardParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        cardParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f); // Span 1 column
        cardParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f); // Span 1 row
        cardParams.setMargins(16, 16, 16, 16);
        cardView.setLayoutParams(cardParams);

        cardView.setRadius(22f); // Rounded corners
        cardView.setCardElevation(6f); // Shadow effect
        cardView.setCardBackgroundColor(Color.WHITE);

        // Create a vertical LinearLayout to hold the image and text
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(0, 0, 0, 0);
        layout.setGravity(Gravity.CENTER);

        // Add an ImageView
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(item.getImageResId());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                300 // Fixed height for the image
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        layout.addView(imageView);

        // Add a TextView for the item name
        TextView nameView = new TextView(context);
        nameView.setText(item.getName());
        nameView.setTextSize(18);
        nameView.setTextColor(Color.BLACK);
        nameView.setGravity(Gravity.CENTER);
        layout.addView(nameView);

        // Add a TextView for the price
        TextView priceView = new TextView(context);
        priceView.setText(item.getPrice());
        priceView.setTextSize(16);
        priceView.setTextColor(Color.DKGRAY);
        priceView.setGravity(Gravity.CENTER);
        layout.addView(priceView);

        // Add the LinearLayout to the CardView
        cardView.addView(layout);

        return cardView;
    }

}
