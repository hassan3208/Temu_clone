package com.hassan.temu_clone_2;

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

public class all extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Set up a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Create a list of items
        List<items> item = new ArrayList<>();
        item.add(new items(R.drawable.school_bag, "school bag", "$10"));
        item.add(new items(R.drawable.hand_bag, "Hand bag", "$15"));
        item.add(new items(R.drawable.iconic_bag, "Iconic bag", "$20"));
        item.add(new items(R.drawable.toad_bag, "Tote bag", "$10"));
        item.add(new items(R.drawable.shoulder_bag, "Shoulder bag", "$15"));
        item.add(new items(R.drawable.school_bag, "school bag", "$10"));
        item.add(new items(R.drawable.hand_bag, "Hand bag", "$15"));
        item.add(new items(R.drawable.iconic_bag, "Iconic bag", "$20"));
        item.add(new items(R.drawable.toad_bag, "Tote bag", "$10"));
        item.add(new items(R.drawable.shoulder_bag, "Shoulder bag", "$15"));
        item.add(new items(R.drawable.bali, "Earring Bali", "$10"));
        item.add(new items(R.drawable.eraser, "Eraser", "$15"));
        item.add(new items(R.drawable.travel_bag, "Travel bag", "$20"));
        item.add(new items(R.drawable.balt, "belt", "$10"));
        item.add(new items(R.drawable.sunglases, "sun glasses", "$15"));
        item.add(new items(R.drawable.bali, "Earring Bali", "$10"));
        item.add(new items(R.drawable.eraser, "Eraser", "$15"));
        item.add(new items(R.drawable.travel_bag, "Travel bag", "$20"));
        item.add(new items(R.drawable.balt, "belt", "$10"));
        item.add(new items(R.drawable.sunglases, "sun glasses", "$15"));
        item.add(new items(R.drawable.bat, "Bat", "$10"));
        item.add(new items(R.drawable.hardball, "hard ball 156g", "$15"));
        item.add(new items(R.drawable.kit, "Complete kit", "$37"));
        item.add(new items(R.drawable.football, "Football", "$10"));
        item.add(new items(R.drawable.wood_wicket, "wodden wicket", "$15"));
        item.add(new items(R.drawable.baseball, "baseball bat", "$20"));
        item.add(new items(R.drawable.bat, "Bat", "$10"));
        item.add(new items(R.drawable.hardball, "hard ball 156g", "$15"));
        item.add(new items(R.drawable.kit, "Complete kit", "$37"));
        item.add(new items(R.drawable.football, "Football", "$10"));
        item.add(new items(R.drawable.wood_wicket, "wodden wicket", "$15"));
        item.add(new items(R.drawable.baseball, "baseball bat", "$20"));
        item.add(new items(R.drawable.cactus_toy, "cactus toy", "$10"));
        item.add(new items(R.drawable.artbox, "art box", "$15"));
        item.add(new items(R.drawable.craft, "craft accesories", "$20"));
        item.add(new items(R.drawable.fankid, "kids fan", "$10"));
        item.add(new items(R.drawable.dollhouse, "Doll house", "$15"));
        item.add(new items(R.drawable.cactus_toy, "cactus toy", "$10"));
        item.add(new items(R.drawable.artbox, "art box", "$15"));
        item.add(new items(R.drawable.craft, "craft accesories", "$20"));
        item.add(new items(R.drawable.fankid, "kids fan", "$10"));
        item.add(new items(R.drawable.dollhouse, "Doll house", "$15"));
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
        item.add(new items(R.drawable.earbud, "Earbuds", "$10"));
        item.add(new items(R.drawable.wirelesspowerbank, "Wireless Power bank", "$19"));
        item.add(new items(R.drawable.digitalwatch, "Digital Watch", "$20"));
        item.add(new items(R.drawable.headphones, "Headphones", "$21"));
        item.add(new items(R.drawable.aurdinokit, "aurdino kit", "$65"));
        item.add(new items(R.drawable.elkit, "Electronic kit", "$14"));
        item.add(new items(R.drawable.earbud, "Earbuds", "$10"));
        item.add(new items(R.drawable.wirelesspowerbank, "Wireless Power bank", "$19"));
        item.add(new items(R.drawable.digitalwatch, "Digital Watch", "$20"));
        item.add(new items(R.drawable.headphones, "Headphones", "$21"));
        item.add(new items(R.drawable.aurdinokit, "aurdino kit", "$65"));
        item.add(new items(R.drawable.elkit, "Electronic kit", "$14"));
        // Set up the adapter
        ItemsAdapter adapter = new ItemsAdapter(getContext(), item);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
