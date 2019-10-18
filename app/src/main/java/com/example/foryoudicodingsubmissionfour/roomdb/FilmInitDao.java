package com.example.foryoudicodingsubmissionfour.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;


import java.util.List;


import static com.example.foryoudicodingsubmissionfour.provider.MovieProvider.Table_name;
import static com.example.foryoudicodingsubmissionfour.roomdb.Favorite_FilmInit.COLUMN_ID;


@Dao
public interface FilmInitDao {

    @Query("SELECT * FROM favorite_filminit")
    List<Favorite_FilmInit> getAll();



    @Query("SELECT * FROM favorite_filminit WHERE title LIKE :nama ")
    List<Favorite_FilmInit> findByName(String nama);

    @Insert
    void insertAll( Favorite_FilmInit favorite_filmInit );

    @Insert
    long[] insertAl(Favorite_FilmInit[] favorite_filmInits);



    @Query("SELECT COUNT(*) FROM favorite_filminit" )
    int count();

    @Query("SELECT * FROM favorite_filminit WHERE " + COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Select all cheeses.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */
    @Query("SELECT * FROM  favorite_filminit")
    Cursor selectAll();

    @Query("DELETE FROM "+ Table_name +" WHERE " + COLUMN_ID + " = :id")
    int deleteById(long id);


    @Insert
    long insert(Favorite_FilmInit favorite_filmInit);

    @Update
    int updatecon(Favorite_FilmInit favorite_filmInit);

    @Delete
    void deleteUsers(Favorite_FilmInit users);


    @Update
    void update(Favorite_FilmInit nama);

    @Delete
    void deleteAll(Favorite_FilmInit nama1, Favorite_FilmInit nama2);


}
