package com.example.foryoudicodingsubmissionfour.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TvShowInit implements Parcelable {

    public String name;
    public String backdrop_path;
    public String vote_average;
    public String vote_count;
    public String first_air_date;
    public String poster_path;
    public List<String> origin_country;
    public String original_language;
    public String popularity;
    public String overview;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.vote_average);
        dest.writeString(this.vote_count);
        dest.writeString(this.first_air_date);
        dest.writeString(this.poster_path);
        dest.writeStringList(this.origin_country);
        dest.writeString(this.original_language);
        dest.writeString(this.popularity);
        dest.writeString(this.overview);
    }

    public TvShowInit() {
    }

    protected TvShowInit(Parcel in) {
        this.name = in.readString();
        this.backdrop_path = in.readString();
        this.vote_average = in.readString();
        this.vote_count = in.readString();
        this.first_air_date = in.readString();
        this.poster_path = in.readString();
        this.origin_country = in.createStringArrayList();
        this.original_language = in.readString();
        this.popularity = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<TvShowInit> CREATOR = new Creator<TvShowInit>() {
        @Override
        public TvShowInit createFromParcel(Parcel source) {
            return new TvShowInit(source);
        }

        @Override
        public TvShowInit[] newArray(int size) {
            return new TvShowInit[size];
        }
    };
}
