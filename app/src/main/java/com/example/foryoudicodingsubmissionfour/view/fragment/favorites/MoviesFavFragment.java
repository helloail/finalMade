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
import com.example.foryoudicodingsubmissionfour.adapter.favorite.AdapterFilmFav;
import com.example.foryoudicodingsubmissionfour.helper.SwipeItemTouchHelper;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit;

import java.util.ArrayList;
import java.util.List;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.db;

public class MoviesFavFragment extends Fragment {

    List<Favorite_FilmInit> favorite_filmInits = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterFilmFav mAdapter;

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
        mAdapter = new AdapterFilmFav(favorite_filmInits, getContext());
        recyclerView.setAdapter(mAdapter);
        iniTouchHelper();
    }

    private void iniTouchHelper(){
        ItemTouchHelper.Callback callback =  new SwipeItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    private void fetchDataFromRoom() {

        favorite_filmInits = db.filmInitDao().getAll();

        for (int i = 0 ;i <favorite_filmInits.size();i++){
            Log.e("Aplikasi",favorite_filmInits.get(i).getTitle()+i);
        }
        Log.e("cek list",""+favorite_filmInits.size());
    }

}
