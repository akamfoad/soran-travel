package com.fanaye.sorantravel.db.TextInfo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TextInfoDao {
    @Query("SELECT * FROM text_info WHERE info_of_where=:UID AND locale=:locale")
    LiveData<TextInfo> getTextInfoOf(String UID, String locale);
}
