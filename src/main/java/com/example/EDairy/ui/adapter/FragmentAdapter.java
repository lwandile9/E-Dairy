package com.example.EDairy.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.EDairy.ui.fragment.CreateFragment;
import com.example.EDairy.ui.fragment.ReadFragment;
import com.example.EDairy.ui.fragment.UpdateFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments = new Fragment[]{new CreateFragment(), new UpdateFragment(), new ReadFragment()};
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
