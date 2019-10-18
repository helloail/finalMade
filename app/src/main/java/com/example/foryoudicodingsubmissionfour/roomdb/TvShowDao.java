package com.example.foryoudicodingsubmissionfour.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface TvShowDao {

    @Query("SELECT * FROM favorite_tvshow")
    List<Favorite_TvShow> getAll();

    @Query("SELECT * FROM favorite_tvshow WHERE name LIKE :nama ")
    List<Favorite_TvShow> findByName(String nama);

    @Insert
    void insertAll( Favorite_TvShow favorite_tvshow );

    @Delete
    void deleteUsers(Favorite_TvShow nama);

    @Update
    void update(Favorite_TvShow nama);

    @Delete
    void deleteAll(Favorite_TvShow nama1, Favorite_TvShow nama2);

}

