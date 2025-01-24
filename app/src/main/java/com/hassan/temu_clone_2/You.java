package com.hassan.temu_clone_2;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class You extends Fragment {

    public You() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_you, container, false);

        // Now you can safely call findViewById() on the rootView
        TextView setting = rootView.findViewById(R.id.setting);

        // Set the OnClickListener
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(getActivity(), settings_next.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
