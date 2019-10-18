package com.example.foryoudicodingsubmissionfour.adapter.Search;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.model.FilmInit;
import com.example.foryoudicodingsubmissionfour.model.Search.SearchFilmInit;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSearchFilm extends RecyclerView.Adapter<AdapterSearchFilm.MyViewHolder>{
    private ArrayList<SearchFilmInit> list  = new ArrayList<>();
    private Context context;

    public void setData(ArrayList<SearchFilmInit> items, Context context) {
        this.context = context;
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imagelist)
        ImageView imagelist;
        @BindView(R.id.titlevideo)
        TextView titlefilm;
        @BindView(R.id.durasi)TextView durasi;
        @BindView(R.id.ltdurasi)TextView ltdurasi;
        @BindView(R.id.ltsutradara)TextView ltsutradara;
        @BindView(R.id.sutradara)TextView sutradara;
        @BindView(R.id.ratingbar)
        RatingBar rating;
        @BindView(R.id.ly_list)
        LinearLayout lyList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    @NonNull
    @Override
    public AdapterSearchFilm.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        return new AdapterSearchFilm.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchFilm.MyViewHolder myViewHolder, final int i) {
        final SearchFilmInit data = list.get(i);

        String ldurasi = String.format(context.getResources().getString(R.string.Language));
        String lsutradara = String.format(context.getResources().getString(R.string.rilis));

        myViewHolder.ltdurasi.setText(ldurasi);
        myViewHolder.ltsutradara.setText(lsutradara);
        myViewHolder.titlefilm.setText(data.getTitle());
        Tools.displayImageOriginal(context,myViewHolder.imagelist, Config.url_image + data.getPoster_path());
        myViewHolder.sutradara.setText(Tools.displaydate(data.getRelease_date()));
        myViewHolder.durasi.setText(Tools.displaylanguage(data.getOriginal_language()));
        myViewHolder.rating.setRating(Tools.displayrating(Float.parseFloat(data.getVote_average())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
