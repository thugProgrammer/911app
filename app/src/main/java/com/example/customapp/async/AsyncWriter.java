package com.example.customapp.async;

import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class AsyncWriter extends AsyncTask<DatabaseReference, Void, Object> {

    private String uid;
    private HashMap<String, Object> hashmap;
    private String flag;

    public AsyncWriter(String valueUid, HashMap valueMap, String valueFlag){
        uid = valueUid;
        hashmap = valueMap;
        flag = valueFlag;
    }

    @Override
    protected Object doInBackground(DatabaseReference... dbReference) {

        if (flag.equals("UPDATE")){
            dbReference[0].child("Users").child(uid).updateChildren(hashmap);
        }else{
            dbReference[0].child("Users").child(uid);
            dbReference[0].child("Users").child(uid).setValue(hashmap);
        }

        return null;
    }
}
