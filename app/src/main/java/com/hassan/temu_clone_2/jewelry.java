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

public class jewelry extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jewelry, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Set up a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Create a list of items
        List<items> item = new ArrayList<>();
        item.add(new items(R.drawable.neckless, "neckless", "$10"));
        item.add(new items(R.drawable.bangles, "bangles", "$15"));
        item.add(new items(R.drawable.artificial_set, "artifitial set", "$20"));
        item.add(new items(R.drawable.ring, "Ring", "$40"));
        item.add(new items(R.drawable.neckless, "neckless", "$10"));
        item.add(new items(R.drawable.bangles, "bangles", "$15"));
        item.add(new items(R.drawable.artificial_set, "artifitial set", "$20"));
        item.add(new items(R.drawable.ring, "Ring", "$40"));
        item.add(new items(R.drawable.neckless, "neckless", "$10"));
        item.add(new items(R.drawable.bangles, "bangles", "$15"));
        item.add(new items(R.drawable.artificial_set, "artifitial set", "$20"));
        item.add(new items(R.drawable.ring, "Ring", "$40"));

        // Set up the adapter
        ItemsAdapter adapter = new ItemsAdapter(getContext(), item);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
