package com.example.wusandroidlabs.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wusandroidlabs.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
  // public ArrayList<ChatMessage> messages = new ArrayList<>();
  //public static ArrayList<ChatMessage> messages = new ArrayList<>();
  public static MutableLiveData<List<ChatMessage>> messages = new MutableLiveData<>();
  public static MutableLiveData<ChatMessage> selectedMessage = new MutableLiveData<>();
  //public MutableLiveData<ArrayList<String>> messages = new MutableLiveData< >();
}
