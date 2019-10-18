package com.example.foryoudicodingsubmissionfour.view.activity.home;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foryoudicodingsubmissionfour.R;
import com.example.foryoudicodingsubmissionfour.helper.Config;
import com.example.foryoudicodingsubmissionfour.model.TvShowInit;
import com.example.foryoudicodingsubmissionfour.helper.Tools;
import com.example.foryoudicodingsubmissionfour.roomdb.Favorite_TvShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.foryoudicodingsubmissionfour.helper.MyApplication.dbTv;

public class DetailActivityTvShow extends AppCompatActivity {


    @BindView(R.id.imageplayer)
    ImageView imageplayer;
    @BindView(R.id.titlevideo)
    TextView titlevideo;
    @BindView(R.id.imagelist) ImageView imagelist;
    @BindView(R.id.ltfav) ImageView fav;
    @BindView(R.id.ratingbar)
    RatingBar rating;
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

    String ltitle, lreview, ldurasi, lgenre, lnegara, ldeskripsi, lminute, lsutradara;
    TvShowInit setTv;
    Favorite_TvShow favorite_tvShow;
    List<Favorite_TvShow> favorite_tvShows = new ArrayList<>();


    public static final String setParcerlable = "TvShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initComponent();
        Tools.initstetho(this);
        initToolbar();
        initLocale();
        initImgFav();
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
        lgenre = String.format(getResources().getString(R.string.genre));
        lnegara  = String.format(getResources().getString(R.string.country));
        ldeskripsi  = String.format(getResources().getString(R.string.Storyline));
        lminute  = String.format(getResources().getString(R.string.menit));
        lreview  = String.format(getResources().getString(R.string.review));
        ldurasi  = String.format(getResources().getString(R.string.Language));
        lsutradara  = String.format(getResources().getString(R.string.popularity));

        lttitle.setText(ltitle);
        ltrilis.setText(lgenre);
        ltdurasi.setText(ldurasi);
        ltsutradara.setText(lsutradara);
        ltstoryline.setText(ldeskripsi);
        ltreview.setText(lreview);
    }

    private void initComponent() {

        setTv = getIntent().getParcelableExtra(setParcerlable);
        titlevideo.setText(setTv.getName());
        Tools.displayrating(Float.parseFloat(setTv.getVote_average()));
        reviews.setText(setTv.getVote_count());
        titledesc.setText(setTv.getName());
        rilis.setText(Tools.displaydate(setTv.getFirst_air_date()));
        durasi.setText(Tools.displaylanguage(setTv.getOriginal_language()));
        sutradara.setText(setTv.getPopularity());
        diskripsi.setText(setTv.getOverview());
        Tools.displayImageOriginal(this,imagelist, Config.url_image + setTv.getPoster_path());
        Tools.displayImageOriginal(this,imageplayer, Config.url_image + setTv.getBackdrop_path());

    }

    @OnClick({R.id.ltfav})
    public void imageView(ImageView imageView){
        fetchDataFromRoom();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void InitSave() {
        favorite_tvShow = new Favorite_TvShow();
        favorite_tvShow.setName(setTv.getName());
        favorite_tvShow.setVote_average(setTv.getVote_average());
        favorite_tvShow.setVote_count(setTv.getVote_count());
        favorite_tvShow.setFirst_air_date(setTv.getFirst_air_date());
        favorite_tvShow.setOriginal_language(setTv.getOriginal_language());
        favorite_tvShow.setPopularity(setTv.getPopularity());
        favorite_tvShow.setOverview(setTv.getOverview());
        favorite_tvShow.setBackdrop_path(setTv.getBackdrop_path());
        favorite_tvShow.setPoster_path(setTv.getPoster_path());
        dbTv.tvShowDao().insertAll(favorite_tvShow);
        Tools.initChangeWidget(this);
        Toast.makeText(getApplicationContext(),"Data Tersimapan",Toast.LENGTH_SHORT).show();
        fav.setImageResource(R.drawable.ic_heart_solid);
    }

    private void fetchDataFromRoom() {
        favorite_tvShows = dbTv.tvShowDao().findByName(setTv.getName());

        if (favorite_tvShows.size()==0){
            InitSave();
        }else {
            Toast.makeText(getApplicationContext(),"Data Sudah Ada",Toast.LENGTH_SHORT).show();
        }
    }

    private void initImgFav() {
        favorite_tvShows = dbTv.tvShowDao().findByName(setTv.getName());

        if (favorite_tvShows.size()==0){
            fav.setImageResource(R.drawable.ic_heart_border);
        }else {
            fav.setImageResource(R.drawable.ic_heart_solid);
        }
    }
}
