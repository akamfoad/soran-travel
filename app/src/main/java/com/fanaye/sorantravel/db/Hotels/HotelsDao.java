package com.fanaye.sorantravel.db.Hotels;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HotelsDao {
    @Query("SELECT * FROM hotels")
    LiveData<List<Hotels>> getHotels_s();

    @Query("SELECT * FROM hotels where uniqueid=:UID")
    LiveData<Hotels> getHotel(String UID);
}
