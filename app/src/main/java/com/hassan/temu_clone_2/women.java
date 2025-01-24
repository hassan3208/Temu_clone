package com.hassan.temu_clone_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class women extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_women, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Set up a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Create a list of items
        List<items> item = new ArrayList<>();
        item.add(new items(R.drawable.img, "Item 1", "$10"));
        item.add(new items(R.drawable.img_1, "Item 2", "$15"));
        item.add(new items(R.drawable.img_2, "Item 3", "$20"));
        item.add(new items(R.drawable.img, "Item 1", "$10"));
        item.add(new items(R.drawable.img_1, "Item 2", "$15"));
        item.add(new items(R.drawable.img_2, "Item 3", "$20"));

        // Set up the adapter
        ItemsAdapter adapter = new ItemsAdapter(getContext(), item);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
