package com.fanaye.sorantravel.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Hotels.Hotels;
import com.fanaye.sorantravel.db.Hotels.HotelsDao;
import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Images.ImagesDao;
import com.fanaye.sorantravel.db.TextInfo.TextInfo;
import com.fanaye.sorantravel.db.TextInfo.TextInfoDao;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGo;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGoDao;
import com.fanaye.sorantravel.db.restaurants.Restaurants;
import com.fanaye.sorantravel.db.restaurants.RestaurantsDao;

import java.util.List;

public class Repository {
    private TextInfoDao textInfoDao;
    private WhereToGoDao whereToGoDao;
    private LiveData<List<WhereToGo>> whereToGoList;

    private ImagesDao imagesDao;
    private LiveData<List<Images>> imagesList;

    private HotelsDao hotelsDao;
    private LiveData<List<Hotels>> hotelsList;

    private RestaurantsDao restaurantsDao;
    private LiveData<List<Restaurants>> restaurantsList;

    public Repository(Application application) {
        Database db = Database.getDatabase(application);
        textInfoDao = db.textInfoDao();
        whereToGoDao = db.whereToGoDao();
        whereToGoList = whereToGoDao.getWhereToGo_s();
        imagesDao = db.imagesDao();
        imagesList = imagesDao.getAllImages();
        hotelsDao = db.hotelsDao();
        hotelsList = hotelsDao.getHotels_s();
        restaurantsDao = db.restaurantsDao();
        restaurantsList = restaurantsDao.getRestaurants_s();
    }

    public LiveData<List<WhereToGo>> getAllWhereToGo_s() {
        return whereToGoList;
    }

    public LiveData<WhereToGo> getWhereToGo(String id) {
        return whereToGoDao.getWhereToGo(id);
    }

    public LiveData<List<Images>> getImagesList() {
        return imagesList;
    }

    public LiveData<List<Images>> getAllImagesOf(String id) {
        return imagesDao.getAllImagesOf(id);
    }

    public LiveData<Images> getOneImagesOf(String id) {
        return imagesDao.getOneImagesOf(id);
    }

    public LiveData<Images> getOneImage(String id) {
        return imagesDao.getOneImage(id);
    }

    public LiveData<List<Hotels>> getHotelsList() {
        return hotelsList;
    }

    public LiveData<Hotels> getHotel(String id) {
        return hotelsDao.getHotel(id);
    }

    public LiveData<List<Restaurants>> getRestaurantsList() {
        return restaurantsList;
    }

    public LiveData<Restaurants> getRestaurant(String id) {
        return restaurantsDao.getRestaurant(id);
    }

    public LiveData<TextInfo> getTextInfoOf(String UID, String locale) {
        return textInfoDao.getTextInfoOf(UID, locale);
    }

}
