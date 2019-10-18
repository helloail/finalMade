package com.example.foryoudicodingsubmissionfour.model;


import android.os.Parcel;
import android.os.Parcelable;

public class FilmInit implements Parcelable {

    public String title;
    public String vote_count;
    public String vote_average;
    public String popularity;
    public String poster_path;
    public String original_language;
    public String backdrop_path;
    public String adult;
    public String release_date;
    public String overview;

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

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
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




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.vote_count);
        dest.writeString(this.vote_average);
        dest.writeString(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.adult);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
    }

    public FilmInit() {
    }

    protected FilmInit(Parcel in) {
        this.title = in.readString();
        this.vote_count = in.readString();
        this.vote_average = in.readString();
        this.popularity = in.readString();
        this.poster_path = in.readString();
        this.original_language = in.readString();
        this.backdrop_path = in.readString();
        this.adult = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<FilmInit> CREATOR = new Creator<FilmInit>() {
        @Override
        public FilmInit createFromParcel(Parcel source) {
            return new FilmInit(source);
        }

        @Override
        public FilmInit[] newArray(int size) {
            return new FilmInit[size];
        }
    };
}
