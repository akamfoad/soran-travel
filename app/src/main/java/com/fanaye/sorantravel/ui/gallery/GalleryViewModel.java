package com.fanaye.sorantravel.ui.gallery;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Images>> images;

    public GalleryViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        images = repository.getImagesList();
    }


    public LiveData<List<Images>> getAllImages() {
        return images;
    }
}