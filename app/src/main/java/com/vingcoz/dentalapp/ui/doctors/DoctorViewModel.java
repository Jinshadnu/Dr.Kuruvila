package com.vingcoz.dentalapp.ui.doctors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoctorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DoctorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is doctors fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}