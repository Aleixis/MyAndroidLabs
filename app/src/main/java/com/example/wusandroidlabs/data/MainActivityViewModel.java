package com.example.wusandroidlabs.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wusandroidlabs.ChatMessage;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {
  // public ArrayList<ChatMessage> messages = new ArrayList<>();
    public MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData< >();
}
