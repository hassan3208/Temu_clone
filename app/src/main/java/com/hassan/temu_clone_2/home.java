package com.hassan.temu_clone_2;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hassan.temu_clone_2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class home extends Fragment {


    TabLayout tab;
    ViewPager viewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home, container, false);

        // Enable Edge-to-Edge if necessary
//        EdgeToEdge.enable(getActivity());

        // Find the views within the inflated layout
        TabLayout tab = view.findViewById(R.id.tab);
        ViewPager viewpager = view.findViewById(R.id.viewpager);

        // Create the adapter for ViewPager
        viewpagermessengeradapter adapter = new viewpagermessengeradapter(getChildFragmentManager());
        viewpager.setAdapter(adapter);

        // Set up the TabLayout with the ViewPager
        tab.setupWithViewPager(viewpager);

        return view;
    }

}