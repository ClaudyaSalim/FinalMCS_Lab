package com.example.bluejack_pharmacy_final_mcs.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.bluejack_pharmacy_final_mcs.fragment.HomeFragment;
import com.example.bluejack_pharmacy_final_mcs.fragment.TransactionFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private Fragment homeFragment, transactionFragment;

    private Fragment[] fragments;
    private final String[] titles = {"Home", "Transaction"};

    public PagerAdapter(@NonNull FragmentManager fm) { super(fm); }

    public void passUser(int userId){
        homeFragment = HomeFragment.newInstance();
        transactionFragment = TransactionFragment.newInstance(userId);
        fragments = new Fragment[]{homeFragment, transactionFragment};
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
