package com.fanaye.sorantravel.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fanaye.sorantravel.db.Hotels.Hotels;
import com.fanaye.sorantravel.db.Hotels.HotelsDao;
import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Images.ImagesDao;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGo;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGoDao;
import com.fanaye.sorantravel.db.restaurants.Restaurants;
import com.fanaye.sorantravel.db.restaurants.RestaurantsDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {WhereToGo.class, Images.class, Hotels.class, Restaurants.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile Database INSTANCE;
    private static Database.Callback sRoomDatabaseCallback = new Database.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            databaseWriteExecutor.execute(() -> {
                WhereToGoDao dao = INSTANCE.whereToGoDao();
            });
        }
    };

    static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.
                            databaseBuilder(context.getApplicationContext(), Database.class, "soran_travel")
                            .addCallback(sRoomDatabaseCallback)
                            .createFromAsset("soran_travel.sqlite")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WhereToGoDao whereToGoDao();

    public abstract ImagesDao imagesDao();

    public abstract HotelsDao hotelsDao();

    public abstract RestaurantsDao restaurantsDao();
}
