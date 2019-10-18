package com.example.foryoudicodingsubmissionfour.adapter.favorite;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.helper.onSwipeHelper;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_TvShow;
import com.example.foryoudicodingsubmissionfour.widget.WidgetReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.dbTv;

public class AdapterTvShowFav extends RecyclerView.Adapter<AdapterTvShowFav.MyViewHolder> implements onSwipeHelper {
    private List<Favorite_TvShow> list;
    private Context context;


    public AdapterTvShowFav(List<Favorite_TvShow> list,Context context){
        this.context = context;
        this.list = list;
    }

    public void refill(List<Favorite_TvShow> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);

        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageplay)ImageView imageplay;
        @BindView(R.id.titletv)TextView titletv;
        @BindView(R.id.ratingbar)RatingBar rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    @NonNull
    @Override
    public AdapterTvShowFav.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_tv, viewGroup, false);

        return new AdapterTvShowFav.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTvShowFav.MyViewHolder myViewHolder, final int i) {
        final Favorite_TvShow data = list.get(i);
        myViewHolder.titletv.setText(data.getName());
        myViewHolder.rating.setRating(Tools.displayrating(Float.parseFloat(data.getVote_average())));
        Tools.displayImageOriginal(context,myViewHolder.imageplay, Config.url_image + data.getBackdrop_path());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemDismiss(int i) {
        Favorite_TvShow favorite_tvShow = new Favorite_TvShow();
        favorite_tvShow.setNote_id(list.get(i).getNote_id());
        dbTv.tvShowDao().deleteUsers(favorite_tvShow);
        list.remove(i);
        notifyItemRemoved(i);


    }
}