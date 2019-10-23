package com.example.foryoudicodingsubmissionfour.view.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.foryoudicodingsubmissionfour.adapter.main.AdapterList;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.model.FilmInit;
import com.example.foryoudicodingsubmissionfour.view.activity.search.SearchActivityTvShow;
import com.example.foryoudicodingsubmissionfour.adapter.main.AdapterTvShow;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;

import com.example.foryoudicodingsubmissionfour.helper.MyApplication;
import com.example.foryoudicodingsubmissionfour.helper.ViewAnimation;
import com.example.foryoudicodingsubmissionfour.model.TvShowInit;
import com.example.foryoudicodingsubmissionfour.viewmodel.MoviesViewModel;
import com.example.foryoudicodingsubmissionfour.viewmodel.TvShowViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterTvShow mAdapter;
    private TvShowViewModel moviesViewModel;
    private LinearLayout lyt_progress;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
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

    private void  initRecyclerview(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initViewModel(){
        mAdapter = new AdapterTvShow();
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);
        moviesViewModel.setTvShow();
        showLoading(true);

        moviesViewModel.getListTvShow().observe(this, new Observer<ArrayList<TvShowInit>>() {
            @Override
            public void onChanged(ArrayList<TvShowInit> tvShowInits) {
            setmAdapter(tvShowInits);
            }
        });
    }

    private void setmAdapter(ArrayList<TvShowInit> tvShowInits ) {
        if (tvShowInits == null) {
            Toast.makeText(getContext(), "Cannot Conect To Server", Toast.LENGTH_LONG).show();
        }

        if (tvShowInits.size() <= 0) {
            Toast.makeText(getContext(), "no data available", Toast.LENGTH_LONG).show();
        }
        if (tvShowInits != null) {
            mAdapter.setData(tvShowInits, getContext());
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
    public void Saearch(CardView cardView){
        final Intent intent = new Intent(getContext(), SearchActivityTvShow.class);
        intent.putExtra("url",Config.url_serachtvshow);
        getContext().startActivity(intent);

    }
}
