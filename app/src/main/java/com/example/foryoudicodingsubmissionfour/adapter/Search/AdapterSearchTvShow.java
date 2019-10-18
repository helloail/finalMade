package com.example.foryoudicodingsubmissionfour.adapter.Search;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.model.Search.SearchFilmInit;
import com.example.foryoudicodingsubmissionfour.model.Search.SearchTvShowInit;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSearchTvShow extends RecyclerView.Adapter<AdapterSearchTvShow.MyViewHolder>{
    private ArrayList<SearchTvShowInit> list = new ArrayList<>();
    private Context context;

    public void setData(ArrayList<SearchTvShowInit> items, Context context) {
        this.context = context;
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageplay)
        ImageView imageplay;
        @BindView(R.id.titletv)
        TextView titletv;
        @BindView(R.id.ratingbar)
        RatingBar rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    @NonNull
    @Override
    public AdapterSearchTvShow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_tv, viewGroup, false);

        return new AdapterSearchTvShow.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchTvShow.MyViewHolder myViewHolder, final int i) {
        final SearchTvShowInit data = list.get(i);
        myViewHolder.titletv.setText(data.getName());
        myViewHolder.rating.setRating(Tools.displayrating(Float.parseFloat(data.getVote_average())));
        Tools.displayImageOriginal(context,myViewHolder.imageplay, Config.url_image + data.getBackdrop_path());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

