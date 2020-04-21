package com.fanaye.sorantravel.db.WhereToGo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WhereToGoDao {
    @Query("SELECT * FROM where_to_go")
    LiveData<List<WhereToGo>> getWhereToGo_s();

    @Query("SELECT * FROM where_to_go where uniqueid=:UID")
    LiveData<WhereToGo> getWhereToGo(String UID);
}
