package com.example.customapp.ui.shareModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<String> address;
    private MutableLiveData<String> phone;
    private MutableLiveData<String> email;
    private MutableLiveData<String> age;

    public void setDetails(String valueName, String valueAddress, String valuePhone, String valueEmail, String valueAge){
        name.setValue(valueName);
        address.setValue(valueAddress);
        phone.setValue(valuePhone);
        email.setValue(valueEmail);
        age.setValue(valueAge);
    }

    public ShareViewModel() {
        name = new MutableLiveData<>();
        address = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        email = new MutableLiveData<>();
        age = new MutableLiveData<>();
    }

    public LiveData<String> getName() {
        return name;
    }
}
