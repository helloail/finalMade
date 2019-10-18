package com.example.foryoudicodingsubmissionfour.view.fragment.favorites;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.adapter.AdapterTabLayout;

public class FavoritFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_favorite, container, false);
        initComponent(view);
        return  view;
    }

    private void initComponent(View v){
        ViewPager viewPager = v.findViewById(R.id.viewpagerfav);
        setupViewPager(viewPager);
        TabLayout tabLayout = v.findViewById(R.id.tablayoutfav);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterTabLayout adapter = new AdapterTabLayout(getChildFragmentManager());
        String ltvshow  = String.format(getResources().getString(R.string.tvshow));
        String movies  = String.format(getResources().getString(R.string.movie));
        adapter.addFragment(new MoviesFavFragment(), movies);
        adapter.addFragment(new TvShowFavFragment(), ltvshow);
        viewPager.setAdapter(adapter);
    }

}
