package com.fanaye.sorantravel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;

class ImageViewerViewModel extends AndroidViewModel {
    private Repository repository;

    public ImageViewerViewModel(Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Images> getOneImage(String UID) {
        return repository.getOneImage(UID);
    }

}
