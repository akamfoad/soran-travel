package com.fanaye.sorantravel.ui.wtgShow;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fanaye.sorantravel.db.Images.Images;
import com.fanaye.sorantravel.db.Repository;
import com.fanaye.sorantravel.db.TextInfo.TextInfo;
import com.fanaye.sorantravel.db.WhereToGo.WhereToGo;

import java.util.List;

public class WTGShowViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<WhereToGo> whereToGo;
    private LiveData<List<Images>> images;

    public WTGShowViewModel(Application application, String UID) {
        super(application);
        repository = new Repository(application);
        whereToGo = repository.getWhereToGo(UID);
        images = repository.getAllImagesOf(UID);
    }

    public LiveData<WhereToGo> getWhereToGo() {
        return whereToGo;
    }

    public LiveData<List<Images>> getImages() {
        return images;
    }

    public LiveData<TextInfo> getTextInfoOf(String UID, String locale) {
        return repository.getTextInfoOf(UID, locale);
    }
}
