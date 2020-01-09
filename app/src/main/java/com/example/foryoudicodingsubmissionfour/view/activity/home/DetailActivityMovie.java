package com.example.foryoudicodingsubmissionfour.view.activity.home;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.model.FilmInit;
import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit;
import com.example.foryoudicodingsubmissionfour.widget.WidgetReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.db;

public class DetailActivityMovie extends AppCompatActivity {

    @BindView(R.id.imageplayer) ImageView imageplayer;
    @BindView(R.id.ltfav) ImageView fav;
    @BindView(R.id.titlevideo) TextView titlevideo;
    @BindView(R.id.imagelist) ImageView imagelist;
    @BindView(R.id.ratingbar) RatingBar rating;
    @BindView(R.id.reviews) TextView reviews;
    @BindView(R.id.titledesc) TextView titledesc;
    @BindView(R.id.RuningTime) TextView durasi;
    @BindView(R.id.ReleaseDate) TextView rilis;
    @BindView(R.id.director) TextView sutradara;
    @BindView(R.id.discription) TextView diskripsi;
    @BindView(R.id.ltdurasi) TextView ltdurasi;
    @BindView(R.id.ltreview) TextView ltreview;
    @BindView(R.id.ltrilis) TextView ltrilis;
    @BindView(R.id.ltstoryline) TextView ltstoryline;
    @BindView(R.id.ltsutradara) TextView ltsutradara;
    @BindView(R.id.lttitle) TextView lttitle;

    String ltitle, lreview, ldurasi, lrilis, lsutradara, ldeskripsi, lminute;
    FilmInit filmInit;

    Favorite_FilmInit favorite_filmInit;
    List<Favorite_FilmInit> favorite_filmInits = new ArrayList<>();

    public static final String setParcerlable = "film";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmatch);
        ButterKnife.bind(this);
        initComponent();
        Tools.initstetho(getApplicationContext());
        initToolbar();
        initLocale();
        initImgFav();
    }

    @OnClick({R.id.ltfav})
    public void imageView(ImageView imageView){
       fetchDataFromRoom();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_900);
    }


    private void initLocale(){
        ltitle = String.format(getResources().getString(R.string.Judul));
        lrilis = String.format(getResources().getString(R.string.rilis));
        ldurasi  = String.format(getResources().getString(R.string.Language));
        lsutradara  = String.format(getResources().getString(R.string.popularity));
        ldeskripsi  = String.format(getResources().getString(R.string.Storyline));
        lminute  = String.format(getResources().getString(R.string.menit));
        lreview  = String.format(getResources().getString(R.string.review));

        lttitle.setText(ltitle);
        ltrilis.setText(lrilis);
        ltdurasi.setText(ldurasi);
        ltsutradara.setText(lsutradara);
        ltstoryline.setText(ldeskripsi);
        ltreview.setText(lreview);
    }

    private void initComponent() {
        filmInit = getIntent().getParcelableExtra(setParcerlable);
        titlevideo.setText(filmInit.getTitle());
        rating.setRating(Tools.displayrating(Float.parseFloat(filmInit.getVote_average())));
        reviews.setText(filmInit.getVote_count());
        titledesc.setText(filmInit.getTitle());
        durasi.setText(Tools.displaylanguage(filmInit.getOriginal_language()));
        rilis.setText(Tools.displaydate(filmInit.getRelease_date()));
        sutradara.setText(filmInit.getPopularity());
        diskripsi.setText(filmInit.getOverview());
        Tools.displayImageOriginal(this,imagelist, Config.url_image + filmInit.getPoster_path());
        Tools.displayImageOriginal(this,imageplayer, Config.url_image + filmInit.getBackdrop_path());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void InitSave(){
        favorite_filmInit = new Favorite_FilmInit();
        favorite_filmInit.setTitle(filmInit.getTitle());
        favorite_filmInit.setVote_average(filmInit.getVote_average());
        favorite_filmInit.setVote_count(filmInit.getVote_count());
        favorite_filmInit.setOriginal_language(filmInit.getOriginal_language());
        favorite_filmInit.setRelease_date(filmInit.getRelease_date());
        favorite_filmInit.setPopularity(filmInit.getPopularity());
        favorite_filmInit.setOverview(filmInit.getOverview());
        favorite_filmInit.setBackdrop_path(filmInit.getBackdrop_path());
        favorite_filmInit.setPoster_path(filmInit.getPoster_path());
        db.filmInitDao().insertAll(favorite_filmInit);
        Toast.makeText(getApplicationContext(),"Data Tersimapan",Toast.LENGTH_SHORT).show();
        fav.setImageResource(R.drawable.ic_heart_solid);

        Intent widgetUpdateIntent = new Intent(this, WidgetReceiver.class);
        widgetUpdateIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), WidgetReceiver.class));
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(widgetUpdateIntent);

    }
    private void fetchDataFromRoom() {
        favorite_filmInits = db.filmInitDao().findByName(filmInit.getTitle());
        if (favorite_filmInits.size()==0){
            InitSave();
        }else {
            Toast.makeText(getApplicationContext(),"Data Sudah Ada",Toast.LENGTH_SHORT).show();
        }
    }

    private void initImgFav() {
        favorite_filmInits = db.filmInitDao().findByName(filmInit.getTitle());
        if (favorite_filmInits.size()==0){
            fav.setImageResource(R.drawable.ic_heart_border);
        }else {
            fav.setImageResource(R.drawable.ic_heart_solid);
        }
    }
}
