package com.fanaye.sorantravel.ui.resturants;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;
import com.fanaye.sorantravel.db.restaurants.Restaurants;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Restaurants>> restaurants;
    private LiveData<List<Images>> images;

    RestaurantViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        restaurants = repository.getRestaurantsList();
        images = repository.getImagesList();
    }

    public LiveData<List<Restaurants>> getRestaurants() {
        return restaurants;
    }

    public LiveData<Restaurants> getRestaurant(String UID) {
        return repository.getRestaurant(UID);
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
