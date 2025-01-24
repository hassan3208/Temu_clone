package com.hassan.temu_clone_2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class search extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private List<items> filteredItems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize views
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Fetch all items from the static list in the items class
        List<items> allItems = items.getItemList();
        filteredItems = new ArrayList<>(allItems);

        // Set up RecyclerView with GridLayoutManager for 2 columns
        itemsAdapter = new ItemsAdapter(getContext(), filteredItems);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(itemsAdapter);

        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Handled dynamically in onQueryTextChange
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterItems(newText, allItems);
                return true;
            }
        });

        return view;
    }

    private void filterItems(String query, List<items> allItems) {
        filteredItems.clear();
        if (!TextUtils.isEmpty(query)) {
            for (items item : allItems) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        } else {
            filteredItems.addAll(allItems);
        }
        itemsAdapter.notifyDataSetChanged();
    }
}
