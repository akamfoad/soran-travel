package com.fanaye.sorantravel.ui.hotels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Hotels.Hotels;
import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;

import java.util.List;

public class HotelsViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Hotels>> hotels;
    private LiveData<List<Images>> images;

    public HotelsViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        hotels = repository.getHotelsList();
        images = repository.getImagesList();
    }

    public LiveData<List<Hotels>> getHotels() {
        return hotels;
    }

    public LiveData<Hotels> getHotel(String UID) {
        return repository.getHotel(UID);
    }


    public LiveData<List<Images>> getAllImages() {
        return images;
    }

    public LiveData<List<Images>> getAllImagesOf(String UID) {
        return repository.getAllImagesOf(UID);
    }

    public LiveData<Images> getOneImagesOf(String UID) {
        return repository.getOneImagesOf(UID);
    }


}