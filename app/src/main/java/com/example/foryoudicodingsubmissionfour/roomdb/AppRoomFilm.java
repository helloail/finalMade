package com.example.foryoudicodingsubmissionfour.roomdb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Favorite_FilmInit.class}, version = 5)
public abstract class AppRoomFilm extends RoomDatabase {

    public abstract FilmInitDao filmInitDao();

    private static AppRoomFilm sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized AppRoomFilm getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppRoomFilm.class, "film")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

}

