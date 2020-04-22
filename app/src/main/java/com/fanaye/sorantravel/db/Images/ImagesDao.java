package com.fanaye.sorantravel.db.Images;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImagesDao {
    @Query("SELECT * FROM images")
    LiveData<List<Images>> getAllImages();

    @Query("SELECT * FROM images WHERE image_of_where = :UID")
    LiveData<List<Images>> getAllImagesOf(String UID);

    @Query("SELECT * FROM images WHERE image_of_where = :UID LIMIT 1")
    LiveData<Images> getOneImagesOf(String UID);
}
