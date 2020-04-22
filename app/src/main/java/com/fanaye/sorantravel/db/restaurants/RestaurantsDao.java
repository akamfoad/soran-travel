package com.fanaye.sorantravel.db.restaurants;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    LiveData<List<Restaurants>> getRestaurants_s();

    @Query("SELECT * FROM restaurants where uniqueid=:UID")
    LiveData<Restaurants> getRestaurant(String UID);
}
