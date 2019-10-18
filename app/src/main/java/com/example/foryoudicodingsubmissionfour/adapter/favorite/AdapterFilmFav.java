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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.helper.onSwipeHelper;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit;
import com.example.foryoudicodingsubmissionfour.widget.WidgetReceiver;
import com.example.foryoudicodingsubmissionfour.widget.WidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.db;

public class AdapterFilmFav extends RecyclerView.Adapter<AdapterFilmFav.MyViewHolder> implements onSwipeHelper {
    private List<Favorite_FilmInit> list;
    private Context context;


    public AdapterFilmFav(List<Favorite_FilmInit> list,Context context){
        this.context = context;
        this.list = list;
    }

    public void refill(List<Favorite_FilmInit> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);

        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imagelist)ImageView imagelist;
        @BindView(R.id.titlevideo)TextView titlefilm;
        @BindView(R.id.durasi)TextView durasi;
        @BindView(R.id.ltdurasi)TextView ltdurasi;
        @BindView(R.id.ltsutradara)TextView ltsutradara;
        @BindView(R.id.sutradara)TextView sutradara;
        @BindView(R.id.ratingbar)RatingBar rating;
        @BindView(R.id.ly_list)LinearLayout lyList;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }
    @NonNull
    @Override
    public AdapterFilmFav.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        return new AdapterFilmFav.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFilmFav.MyViewHolder myViewHolder, final int i) {
        final Favorite_FilmInit data = list.get(i);

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

    @Override
    public void onItemDismiss(int i) {
        Favorite_FilmInit favorite_filmInit = new Favorite_FilmInit();
        favorite_filmInit.setNote_id(list.get(i).getNote_id());
        db.filmInitDao().deleteUsers(favorite_filmInit);
        Tools.initChangeWidget(context);

        Intent widgetUpdateIntent = new Intent(context, WidgetReceiver.class);
        widgetUpdateIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetReceiver.class));
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        context.sendBroadcast(widgetUpdateIntent);
        list.remove(i);
        notifyItemRemoved(i);

    }
}
