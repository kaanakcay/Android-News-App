package edu.sabanciuniv.operatingsystemsexample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentE();
            case 1:
                return new FragmentS();
            case 2:
                return new FragmentP();
            default:
                return new FragmentE();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
