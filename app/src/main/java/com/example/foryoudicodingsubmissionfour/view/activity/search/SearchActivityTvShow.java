package com.example.foryoudicodingsubmissionfour.view.activity.search;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.adapter.Search.AdapterSearchTvShow;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.model.Search.SearchTvShowInit;
import com.example.foryoudicodingsubmissionfour.viewmodel.SearchTvShowViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivityTvShow extends AppCompatActivity implements  android.widget.SearchView.OnQueryTextListener {

    @BindView(R.id.sc_film) android.widget.SearchView sc_film;
    @BindView(R.id.search_recycler) RecyclerView recyclerView;
    private AdapterSearchTvShow mAdapter;
    private SearchTvShowViewModel searchTvShowViewModel;
    private String url;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Tools.initstetho(this);
        initToolbar();
        initSearch();
        initRecyclerview();
        initViewModel();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("eratel",newText);
        searchTvShowViewModel.setSearch(url,newText);
        return true;
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_900);
    }

    private void initSearch(){
        url = getIntent().getExtras().getString("url");
        sc_film.setQueryHint("Search Tv Show...");
        sc_film.setIconified(false);
        sc_film.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void  initRecyclerview(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AdapterSearchTvShow();
        recyclerView.setAdapter(mAdapter);
        searchTvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(SearchTvShowViewModel.class);
    }

    private void initViewModel(){
        searchTvShowViewModel.getSearch().observe(this, new Observer<ArrayList<SearchTvShowInit>>() {
            @Override
            public void onChanged(ArrayList<SearchTvShowInit> filmInits) {
                setmAdapter(filmInits);
            }
        });
    }

    private void setmAdapter(ArrayList<SearchTvShowInit> filmInits ) {
        if (filmInits == null) {
            Toast.makeText(this, "Cannot Conect To Server", Toast.LENGTH_LONG).show();
        }
        if (filmInits.size() <= 0) {
            Toast.makeText(this, "no data available", Toast.LENGTH_LONG).show();
        }
        if (filmInits != null) {
            mAdapter.setData(filmInits, this);
        }
    }

}