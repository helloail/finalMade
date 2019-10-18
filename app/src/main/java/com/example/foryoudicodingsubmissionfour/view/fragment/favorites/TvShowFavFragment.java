package com.example.foryoudicodingsubmissionfour.view.fragment.favorites;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.adapter.favorite.AdapterTvShowFav;
import com.example.foryoudicodingsubmissionfour.helper.SwipeItemTouchHelper;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_TvShow;


import java.util.ArrayList;
import java.util.List;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.dbTv;

public class TvShowFavFragment extends Fragment {

    List<Favorite_TvShow> favorite_tvShows = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterTvShowFav mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fav, container, false);
        Tools.initstetho(getContext());
        fetchDataFromRoom();
        return v ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mAdapter = new AdapterTvShowFav(favorite_tvShows, getContext());
        recyclerView.setAdapter(mAdapter);
        iniTouchHelper();
    }

    private void iniTouchHelper(){
        ItemTouchHelper.Callback callback =  new SwipeItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void fetchDataFromRoom() {

        favorite_tvShows = dbTv.tvShowDao().getAll();

        for (int i = 0 ;i <favorite_tvShows.size();i++){
            Log.e("Aplikasi",favorite_tvShows.get(i).getName()+i);
        }
        Log.e("cek list",""+favorite_tvShows.size());
    }

}
