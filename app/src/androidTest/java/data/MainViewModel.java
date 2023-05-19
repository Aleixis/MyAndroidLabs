package data;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ui.MainActivity;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<>();

}
