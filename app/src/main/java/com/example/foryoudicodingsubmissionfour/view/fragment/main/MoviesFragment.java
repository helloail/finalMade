package com.example.foryoudicodingsubmissionfour.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.view.activity.search.SearchActivityFilm;
import com.example.foryoudicodingsubmissionfour.adapter.main.AdapterList;
import com.example.foryoudicodingsubmissionfour.helper.ViewAnimation;
import com.example.foryoudicodingsubmissionfour.model.FilmInit;
import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.viewmodel.MoviesViewModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterList mAdapter;
    private LinearLayout lyt_progress;
    private MoviesViewModel moviesViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        Tools.initstetho(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        lyt_progress = view.findViewById(R.id.lyt_progress);
        initRecyclerview();
        initViewModel();
    }

    private void initRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initViewModel() {
        mAdapter = new AdapterList();
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        moviesViewModel.setListmovie();
        showLoading(true);

        moviesViewModel.getListMovie().observe(this, new Observer<ArrayList<FilmInit>>() {
            @Override
            public void onChanged(ArrayList<FilmInit> filmInits) {
                setmAdapter(filmInits);
            }
        });
    }

    private void setmAdapter(ArrayList<FilmInit> filmInits) {
        if (filmInits == null) {
            Toast.makeText(getContext(), "Cannot Conect To Server", Toast.LENGTH_LONG).show();
        }

        if (filmInits.size() <= 0) {
            Toast.makeText(getContext(), "no data available", Toast.LENGTH_LONG).show();
        }
        if (filmInits != null) {
            mAdapter.setData(filmInits, getContext());
            showLoading(false);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            lyt_progress.setVisibility(View.VISIBLE);
            lyt_progress.setAlpha(1.0f);
            recyclerView.setVisibility(View.GONE);
        } else {
            ViewAnimation.fadeOut(lyt_progress);
            lyt_progress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.search_bar})
    public void Saearch(CardView cardView) {
        final Intent intent = new Intent(getContext(), SearchActivityFilm.class);
        intent.putExtra("url", Config.url_serachmovie);
        getContext().startActivity(intent);

    }

}
