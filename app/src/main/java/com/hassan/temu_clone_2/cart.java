package com.hassan.temu_clone_2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cart extends Fragment {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private List<items> itemList = new ArrayList<>();
    private List<items> pendingItems = new ArrayList<>(); // List for items added before initialization

    public cart() {
        // Ensure RecyclerView is initialized by default
        adapter = new ItemsAdapter(getContext(), itemList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize total price
        final int[] total = {0};

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView emptyTextView = view.findViewById(R.id.emptyTextView); // TextView for "No items in the cart."
        TextView totalTextView = view.findViewById(R.id.total_price); // TextView to display total price

        // Set up a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // List to store items
        List<items> itemList = new ArrayList<>();

        // Adapter for the RecyclerView
        ItemsAdapter adapter = new ItemsAdapter(getContext(), itemList);
        recyclerView.setAdapter(adapter);

        // Reference to Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText("No items in the cart.");
            recyclerView.setVisibility(View.GONE);
            totalTextView.setVisibility(View.GONE); // Hide total price when no user is logged in
            return view;
        }

        String userId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("Cart");

        // Fetch items from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear(); // Clear the list to avoid duplicates
                total[0] = 0; // Reset the total price to avoid accumulation

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        // Retrieve item data
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String price = dataSnapshot.child("price").getValue(String.class);
                        int image = dataSnapshot.child("image").getValue(Integer.class);

                        // Parse and add price to total
                        if (price != null) {
                            try {
                                // Remove the $ symbol or any non-numeric characters from the price
                                String numericPrice = price.replaceAll("[^\\d]", "");
                                total[0] += Integer.parseInt(numericPrice);
                            } catch (NumberFormatException e) {
                                Log.e("ParseError", "Invalid price format: " + price);
                            }
                        }

                        // Add to item list
                        itemList.add(new items(image, name, price));
                    }

                    // Update the adapter
                    adapter.notifyDataSetChanged();

                    // Hide the empty text view if items are present
                    emptyTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    // Update the total price TextView
                    totalTextView.setVisibility(View.VISIBLE);
                    totalTextView.setText("Total Price: $" + total[0]);
                } else {
                    // If no items exist, show the empty text view
                    emptyTextView.setVisibility(View.VISIBLE);
                    emptyTextView.setText("No items in the cart.");
                    recyclerView.setVisibility(View.GONE);

                    // Hide the total price TextView
                    totalTextView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to fetch items: " + error.getMessage());
            }
        });

        return view;
    }






    public void addToFirebase(int image, String name, String price) {
        // Get the currently authenticated user ID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            Log.e("Firebase", "User not authenticated.");
            return;
        }

        String userId = currentUser.getUid(); // Get the current user's unique ID

        // Reference to Firebase Realtime Database for the specific user
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("Cart");

        // Generate a unique key for the cart item
        String itemId = databaseReference.push().getKey();

        if (itemId == null) {
            Log.e("Firebase", "Failed to generate a unique key for the cart item.");
            return;
        }

        // Create a map to store the item details
        HashMap<String, Object> cartItem = new HashMap<>();
        cartItem.put("image", image); // Store image as a resource ID or URL
        cartItem.put("name", name);
        cartItem.put("price", price);

        // Save the cart item in Firebase under the generated key
        databaseReference.child(itemId).setValue(cartItem).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Firebase", "Item added to cart in Firebase.");
            } else {
                Log.e("Firebase", "Failed to add item to cart in Firebase.", task.getException());
            }
        });
    }

}