package com.example.customapp.async;

import android.app.Activity;
import android.os.AsyncTask;

public class AsyncPerformer extends AsyncTask {

    private String flag;
    private Activity activity;

    public AsyncPerformer(Activity valueActivity){
        activity = valueActivity;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

//        switch (flag){
//            case : ;
//            break;
//        }

        return null;
    }


    public void read(){

    }


    public void setFlag(String flag) {
        this.flag = flag;
    }
}
