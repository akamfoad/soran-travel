package com.fanaye.sorantravel.ui.videos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VideosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is videos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}