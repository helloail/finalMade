package com.example.foryoudicodingsubmissionfour.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.content.ContentValues;
import android.provider.BaseColumns;

@Entity
public class Favorite_FilmInit {


    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VOTECOUNT = "vote_count";
    public static final String COLUMN_VOTEAVERAGE = "vote_average";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_RELEASEDATE = "release_date";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_ORIGINALlANGUAGE = "original_language";
    public static final String COLUMN_BACKDROPHPATH = "backdrop_path";
    public static final String COLUMN_POSTERPATH = "poster_path";


    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long note_id;

    @ColumnInfo(name = COLUMN_TITLE)
    public String title;

    @ColumnInfo(name = COLUMN_VOTECOUNT)
    public String vote_count;

    @ColumnInfo(name = COLUMN_VOTEAVERAGE)
    public String vote_average;

    @ColumnInfo(name = COLUMN_POPULARITY)
    public String popularity;

    @ColumnInfo(name = COLUMN_RELEASEDATE)
    public String release_date;

    @ColumnInfo(name = COLUMN_OVERVIEW)
    public String overview;
    @ColumnInfo(name = COLUMN_POSTERPATH)
    public String poster_path;

    @ColumnInfo(name = COLUMN_ORIGINALlANGUAGE)
    public String original_language;

    @ColumnInfo(name = COLUMN_BACKDROPHPATH)
    public String backdrop_path;

    public static Favorite_FilmInit fromContentValues(ContentValues values) {
        final Favorite_FilmInit favorite_filmInit = new Favorite_FilmInit();

        if (values.containsKey(COLUMN_ID)) {
            favorite_filmInit.note_id = values.getAsLong(COLUMN_ID);
        }
            if (values.containsKey(COLUMN_TITLE)) {
            favorite_filmInit.title = values.getAsString(COLUMN_TITLE);
        }

        if (values.containsKey(COLUMN_VOTECOUNT)) {
            favorite_filmInit.vote_count = values.getAsString(COLUMN_VOTECOUNT);
        }
        if (values.containsKey(COLUMN_VOTEAVERAGE)) {
            favorite_filmInit.vote_average = values.getAsString(COLUMN_VOTEAVERAGE);
        }
        if (values.containsKey(COLUMN_POPULARITY)) {
            favorite_filmInit.popularity = values.getAsString(COLUMN_POPULARITY);
        }
        if (values.containsKey(COLUMN_RELEASEDATE)) {
            favorite_filmInit.release_date = values.getAsString(COLUMN_RELEASEDATE);
        }
        if (values.containsKey(COLUMN_OVERVIEW)) {
            favorite_filmInit.overview = values.getAsString(COLUMN_OVERVIEW);
        }
        if (values.containsKey(COLUMN_POSTERPATH)) {
            favorite_filmInit.poster_path = values.getAsString(COLUMN_POSTERPATH);
        }
        if (values.containsKey(COLUMN_ORIGINALlANGUAGE)) {
            favorite_filmInit.original_language = values.getAsString(COLUMN_ORIGINALlANGUAGE);
        }
        if (values.containsKey(COLUMN_BACKDROPHPATH)) {
            favorite_filmInit.backdrop_path = values.getAsString(COLUMN_BACKDROPHPATH);
        }
        return favorite_filmInit;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
