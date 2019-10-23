package com.example.customapp.ui.shareModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> address = new MutableLiveData<>();
    private MutableLiveData<String> phone = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> age = new MutableLiveData<>();

    public void setDetails(String valueName, String valueAddress, String valuePhone, String valueEmail, String valueAge){
        name.setValue(valueName);
        address.setValue(valueAddress);
        phone.setValue(valuePhone);
        email.setValue(valueEmail);
        age.setValue(valueAge);
    }

    public LiveData<String> getName() {
        return name;
    }
}
