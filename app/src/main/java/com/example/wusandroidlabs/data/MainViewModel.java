package com.example.wusandroidlabs.data;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;





public class MainViewModel extends ViewModel {
public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();

    public MutableLiveData<String> editString = new MutableLiveData<>();


}