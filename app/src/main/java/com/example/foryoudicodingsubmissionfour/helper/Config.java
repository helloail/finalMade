package com.example.foryoudicodingsubmissionfour.helper;

public class Config {
    public static final String  url_movies = "https://api.themoviedb.org/3/discover/movie?api_key=507636b08f78cb257ef0da7f56dc514e&language=en-US";

    public static final String  url_tvshow = "https://api.themoviedb.org/3/discover/tv?api_key=507636b08f78cb257ef0da7f56dc514e&language=en-US";

    public static final String url_image = "https://image.tmdb.org/t/p/w185";

    public static final String url_serachmovie="https://api.themoviedb.org/3/search/movie?api_key=507636b08f78cb257ef0da7f56dc514e&language=en-US&query=";

    public static final String url_serachtvshow = "https://api.themoviedb.org/3/search/tv?api_key=507636b08f78cb257ef0da7f56dc514e&language=en-US&query=";

    public static  final String url_rilis_notif = "https://api.themoviedb.org/3/discover/movie?api_key=507636b08f78cb257ef0da7f56dc514e&primary_release_date.gte="+Tools.curentDate()+"&primary_release_date.lte="+Tools.curentDate();
}
