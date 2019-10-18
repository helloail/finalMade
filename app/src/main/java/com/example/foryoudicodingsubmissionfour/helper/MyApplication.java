package com.example.foryoudicodingsubmissionfour.helper;

import android.app.Application;
import androidx.room.Room;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.foryoudicodingsubmissionfour.roomdb.AppRoomFilm;
import com.example.foryoudicodingsubmissionfour.roomdb.AppRoomTvShow;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();
    public static AppRoomFilm db;
    public static AppRoomTvShow dbTv;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        db = Room.databaseBuilder(getApplicationContext(),
                AppRoomFilm.class,"film")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        dbTv = Room.databaseBuilder(getApplicationContext(),
                AppRoomTvShow.class,"tvshow")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }



}
