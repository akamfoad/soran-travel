package com.fanaye.sorantravel.ui.hotelShow;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Hotels.Hotels;
import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;

import java.util.List;

public class HotelShowViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Hotels> hotels;
    private LiveData<List<Images>> images;

    public HotelShowViewModel(Application application, String UID) {
        super(application);
        repository = new Repository(application);
        hotels = repository.getHotel(UID);
        images = repository.getAllImagesOf(UID);
    }

    public LiveData<Hotels> getHotels() {
        return hotels;
    }

    public LiveData<List<Images>> getImages() {
        return images;
    }
}
