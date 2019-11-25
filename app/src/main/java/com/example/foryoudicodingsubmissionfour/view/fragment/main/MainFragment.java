package com.example.foryoudicodingsubmissionfour.view.fragment.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foryoudicodingsubmissionfour.adapter.AdapterTabLayout;
import com.example.foryoudicodingsubmissionfour.R;


public class MainFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initComponent(view);
        return  view;
    }

    private void initComponent(View v){
        viewPager =  v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout =  v.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterTabLayout adapter = new AdapterTabLayout(getChildFragmentManager());
        String ltvshow  = String.format(getResources().getString(R.string.tvshow));
        String movies  = String.format(getResources().getString(R.string.movie));
        adapter.addFragment(new MoviesFragment(), movies);
        adapter.addFragment(new TvShowFragment(), ltvshow);
        viewPager.setAdapter(adapter);
    }
}