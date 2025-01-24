package com.hassan.temu_clone_2;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewpagermessengeradapter  extends FragmentPagerAdapter {


    public viewpagermessengeradapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new all();
        }
        else if (position == 1) {
            return new men();
        }
        else if (position == 2) {
            return new women();
        }
        else if (position == 3) {
            return new sports();
        }
        else if (position == 4) {
            return new jewelry();
        }
        else if (position == 5) {
            return new kids();
        } else if (position==6) {
            return new bags();
        } else {
            return new electronics();
        }
    }


    @Override
    public int getCount() {
        return 8; // no of tabs
    }



    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "All";
        }
        else if (position == 1) {
            return "men";
        }
        else if (position == 2) {
            return "women";
        }
        else if (position == 3) {
            return "sports";
        }
        else if (position == 4) {
            return "jewelry";
        }
        else if (position == 5) {
            return "kids";
        } else if (position==6) {
            return "bags";

        } else {
            return "electronics";
        }
    }

}

