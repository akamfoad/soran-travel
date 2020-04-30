package com.fanaye.sorantravel.ui.wheretogo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;
import com.fanaye.sorantravel.db.TextInfo.TextInfo;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGo;

import java.util.List;

public class WhereToGoViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<WhereToGo>> whereToGo_s;
    private LiveData<List<Images>> images;

    public WhereToGoViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        whereToGo_s = repository.getAllWhereToGo_s();
        images = repository.getImagesList();
    }

    public LiveData<List<WhereToGo>> getWhereToGo_s() {
        return whereToGo_s;
    }

    public LiveData<WhereToGo> getWhereToGo(String UID) {
        return repository.getWhereToGo(UID);
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

    public LiveData<TextInfo> getTextInfoOf(String UID, String locale) {
        return repository.getTextInfoOf(UID, locale);
    }

}
