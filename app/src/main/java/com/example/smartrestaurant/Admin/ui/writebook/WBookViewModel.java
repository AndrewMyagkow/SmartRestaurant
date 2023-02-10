package com.example.smartrestaurant.Admin.ui.writebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WBookViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}