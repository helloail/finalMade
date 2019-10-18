package com.example.foryoudicodingsubmissionfour.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite_TvShow.class}, version = 1)
public abstract class AppRoomTvShow extends RoomDatabase {
    public abstract TvShowDao tvShowDao();


}
