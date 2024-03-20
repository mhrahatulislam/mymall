package com.example.mymall.ui.myMall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MymallViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MymallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Mall fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
