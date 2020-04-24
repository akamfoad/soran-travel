package com.fanaye.sorantravel.ui.resShow;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;
import com.fanaye.sorantravel.db.restaurants.Restaurants;

import java.util.List;

public class RestaurantShowViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<Restaurants> restaurants;
    private LiveData<List<Images>> images;

    public RestaurantShowViewModel(Application application, String UID) {
        super(application);
        repository = new Repository(application);
        restaurants = repository.getRestaurant(UID);
        images = repository.getAllImagesOf(UID);
    }

    public LiveData<Restaurants> getRestaurant() {
        return restaurants;
    }

    public LiveData<List<Images>> getImages() {
        return images;
    }
}
